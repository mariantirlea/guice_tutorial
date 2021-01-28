public class MyMessageDITest {

    public static void main(String[] args) {

        String msg = "Hi John";
        String email = "john@abc.com";
        String phone = "52345613";

        MessageServiceInjector injector;
        Consumer app;

        //Send email
        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, email);

        //Send SMS
        injector = new SMSServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, phone);

    }

}
