import com.google.inject.*;


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

        bind(SpellChecker.class).to(SpellCheckerImpl.class);
    }

}

@ImplementedBy(SpellCheckerImpl.class)
interface SpellChecker {
    void checkSpelling();
}

class SpellCheckerImpl implements SpellChecker {

    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }
}

