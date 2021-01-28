import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());

        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
        editor.makeSpellCheck();

    }

}

class TextEditor {
    private final SpellChecker spellChecker;

    @Inject
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck(){
        spellChecker.checkSpelling();
    }

}

class TextEditorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);

        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(CallTracker.class),
                new CallTrackerService());
    }

}

interface SpellChecker {
    void checkSpelling();
}

@Singleton
class SpellCheckerImpl implements SpellChecker {

    @Override
    @CallTracker
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CallTracker {}

class CallTrackerService implements MethodInterceptor {

    public CallTrackerService() {
        System.out.println("CallTrackerService constructor");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before " + invocation.getMethod().getName());

        Object result = invocation.proceed();

        System.out.println("After " + invocation.getMethod().getName());

        return result;
    }
}
