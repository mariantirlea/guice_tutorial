public class EmailServiceInjector implements MessageServiceInjector {

    public Consumer getConsumer() {
        MyDIApplication app = new MyDIApplication();
        app.setService(new EmailServiceImpl());

        return app;
    }
}
