import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceTester {

    public static void main(String[] args) {

        /**
         * Guice.createInjector() takes Modules, and returns a new Injector
         * instance. This method is to be called once during application startup.
         */
        Injector injector = Guice.createInjector(new TextEditorModule());

        /**
         * Build object using injector
         */
        TextEditor textEditor = injector.getInstance(TextEditor.class);
    }

    public static class TextEditor {
        private SpellChecker spellChecker;

        @Inject
        public TextEditor(SpellChecker spellChecker) {
            this.spellChecker = spellChecker;
        }

    }

    public interface SpellChecker {

    }

    public static class WinWordSpellChecker implements SpellChecker{

    }

    public static class TextEditorModule extends AbstractModule {

        @Override
        protected void configure() {

            /**
             * Bind SpellChecker binding to WinWordSpellChecker implementation
             * whenever spellChecker dependency is used
             */
            bind(SpellChecker.class).to(WinWordSpellChecker.class);

        }
    }

}
