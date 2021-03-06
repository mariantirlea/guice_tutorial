import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyDIApplicationJUnitTest {

    private MessageServiceInjector injector;

    @Before
    public void setup(){
        //mock the injector with anonymous class
        injector = new MessageServiceInjector() {
            public Consumer getConsumer() {
                MyDIApplication app = new MyDIApplication();
                app.setService(new MessageService() {
                    public void sendMessage(String msg, String rec) {
                        System.out.println("Mock Message Service implementation");
                    }
                });

                return app;
            }
        };
    }

    @Test
    public void test(){
        Consumer consumer = injector.getConsumer();
        consumer.processMessages("Hi Alex", "alex@abc.com");
    }

    @After
    public void tear(){
        injector = null;
    }

}
