import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);

        editor.makeSpellCheck();
    }

    static class TextEditor {
        private SpellChecker spellChecker;

        @Inject
        public TextEditor(SpellChecker spellChecker) {
            this.spellChecker = spellChecker;
        }

        public void makeSpellCheck(){
            spellChecker.checkSpelling();
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

    static class TextEditorModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(SpellChecker.class).to(SpellCheckerImpl.class);
            bind(SpellCheckerImpl.class).to(WinWordSpellCheckerImpl.class);
        }
    }

}
