public class SMSServiceInjector implements MessageServiceInjector {

    public Consumer getConsumer() {

        MyDIApplication app = new MyDIApplication();
        app.setService(new SMSServiceImpl());

        return app;
    }
}
