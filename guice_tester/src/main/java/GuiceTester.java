import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.logging.Logger;


public class GuiceTester {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());

        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();

    }

}

class TextEditor {
    private Logger logger;

    @Inject
    public TextEditor(Logger logger) {
        this.logger = logger;
    }

    public void makeSpellCheck(){
        logger.info("In TextEditor.makeSpellCheck() method");
    }
}

class TextEditorModule extends AbstractModule {

}
