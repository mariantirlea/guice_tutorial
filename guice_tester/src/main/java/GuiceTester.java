import com.google.inject.*;
import com.google.inject.name.Names;

import javax.inject.Named;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);

        editor.makeConnection();
    }

    static class TextEditor {
        private String dbUrl;

        @Inject
        public TextEditor(@Named("JDBC") String dbUrl) {
            this.dbUrl = dbUrl;
        }

        public void makeConnection(){
            System.out.println(dbUrl);
        }
    }

    static class TextEditorModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(String.class)
                    .annotatedWith(Names.named("JDBC"))
                    .toInstance("jdbc:mysql://localhost:5326/emp");
        }
    }

}
