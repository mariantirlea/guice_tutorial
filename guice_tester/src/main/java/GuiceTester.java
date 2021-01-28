import com.google.inject.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
@interface WinWord {}

public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);

        editor.makeSpellCheck();
    }

    static class TextEditor {
        private SpellChecker spellChecker;

        @Inject
        public TextEditor(@WinWord SpellChecker spellChecker) {
            this.spellChecker = spellChecker;
        }

        public void makeSpellCheck(){
            spellChecker.checkSpelling();
        }
    }

    static class TextEditorModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(SpellChecker.class)
                    .annotatedWith(WinWord.class)
                    .to(WinWordSpellCheckerImpl.class);
        }
    }

    interface SpellChecker {
        void checkSpelling();
    }

    static class SpellCheckerImpl implements SpellChecker{

        public void checkSpelling() {
            System.out.println("Inside checkSpelling.");
        }
    }

    static class WinWordSpellCheckerImpl extends SpellCheckerImpl {

        @Override
        public void checkSpelling() {
            System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling.");
        }
    }


}
