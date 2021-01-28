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

    }

}

@ImplementedBy(SpellCheckerImpl.class)
interface SpellChecker {
    void checkSpelling();
}

class SpellCheckerImpl implements SpellChecker {

    private String dbUrl = "jdbc:mysql://localhost:5326/emp";

    @Inject(optional = true)
    public void setDbUrl(@Named("JDBC") String dbUrl){
        this.dbUrl = dbUrl;
    }

    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(dbUrl);
    }
}

