import com.google.inject.*;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());

//        //TODO learn more about injectMembers as it creates another instance and not using the one provided by me
//        SpellChecker spellChecker = new SpellCheckerImpl();
//        System.out.println(spellChecker.getId());
//        injector.injectMembers(spellChecker);

        TextEditor editor = injector.getInstance(TextEditor.class);
        System.out.println(editor.getSpellCheckerId());

        TextEditor editor1 = injector.getInstance(TextEditor.class);
        System.out.println(editor1.getSpellCheckerId());

    }

}

class TextEditor {
    private SpellChecker spellChecker;

    @Inject
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck(){
        spellChecker.checkSpelling();
    }

    public double getSpellCheckerId(){
        return spellChecker.getId();
    }
}

class TextEditorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
    }

}

interface SpellChecker {
    double getId();
    void checkSpelling();
}

@Singleton
class SpellCheckerImpl implements SpellChecker {

    private final double id;

    public SpellCheckerImpl(){
        System.out.println("Default constructor");
        id = Math.random();
    }

    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }

    @Override
    public double getId() {
        return id;
    }
}

