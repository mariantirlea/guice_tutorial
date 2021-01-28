import com.google.inject.*;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);

        editor.makeSpellCheck();
    }

    static class TextEditor {
        private final SpellChecker spellChecker;

        @Inject
        public TextEditor(SpellChecker spellChecker) {
            this.spellChecker = spellChecker;
        }

        public void makeSpellCheck(){
            spellChecker.checkSpelling();
        }
    }

    static class TextEditorModule extends AbstractModule {

        @Provides
        public SpellChecker provideSpellChecker(){

            String dbUrl = "jdbc:mysql://localhost:5326/emp";
            String user = "user";
            int timeout = 100;

            return new SpellCheckerImpl(dbUrl, user, timeout);
        }
    }

    interface SpellChecker {
        void checkSpelling();
    }

    static class SpellCheckerImpl implements SpellChecker {

        private final String dbUrl;
        private final String user;
        private final Integer timeout;

        public SpellCheckerImpl(String dbUrl, String user, Integer timeout) {
            this.dbUrl = dbUrl;
            this.user = user;
            this.timeout = timeout;
        }

        public void checkSpelling() {
            System.out.println("Inside checkSpelling.");
            System.out.println(dbUrl);
            System.out.println(user);
            System.out.println(timeout);
        }
    }

}
