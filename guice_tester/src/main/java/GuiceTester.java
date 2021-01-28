import com.google.inject.*;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());

        //TODO learn more about injectMembers as it creates another instance and not using the one provided by me
        SpellChecker spellChecker = new SpellCheckerImpl();
        System.out.println(spellChecker.hashCode());
        injector.injectMembers(spellChecker);

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

    public SpellCheckerImpl(){
        System.out.println("Default constructor");
    }

    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(this.hashCode());
    }
}

