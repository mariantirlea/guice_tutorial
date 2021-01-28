public class MyLegacyTest {

    public static void main(String[] args){

        MyApplication app = new MyApplication(new EmailService());
        app.processMessages("Hi John", "john@abc.com");

    }

}
