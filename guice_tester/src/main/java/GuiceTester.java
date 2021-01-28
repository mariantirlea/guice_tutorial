import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());

        TextEditor editor = injector.getInstance(TextEditor.class);
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
        try {
            bind(SpellChecker.class).toConstructor(SpellCheckerImpl.class.getConstructor(String.class));
        } catch (NoSuchMethodException | SecurityException e) {
            System.err.println("Required constructor missing");
        }

        bind(String.class)
                .annotatedWith(Names.named("JDBC"))
                .toInstance("jdbc:mysql://localhost:5326/emp");
    }

}

interface SpellChecker {
    void checkSpelling();
}

class SpellCheckerImpl implements SpellChecker {

    private final String dbUrl;

    public SpellCheckerImpl(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(dbUrl);
    }
}

