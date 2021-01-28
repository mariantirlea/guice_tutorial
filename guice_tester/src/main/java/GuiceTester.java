import com.google.inject.*;
import com.google.inject.name.Names;

import javax.inject.Named;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);

        editor.makeSpellCheck();
    }

    static class TextEditor {
        private SpellChecker spellChecker;

        @Inject
        public TextEditor(@Named("OpenOffice") SpellChecker spellChecker) {
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
                    .annotatedWith(Names.named("OpenOffice"))
                    .to(OpenOfficeWordSpellCheckerImpl.class);
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

    static class OpenOfficeWordSpellCheckerImpl extends SpellCheckerImpl {

        @Override
        public void checkSpelling() {
            System.out.println("Inside OpenOfficeWordSpellCheckerImpl.checkSpelling.");
        }
    }


}
