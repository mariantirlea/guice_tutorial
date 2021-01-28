public class EmailServiceInjector implements MessageServiceInjector {

    public Consumer getConsumer() {
        return new MyDIApplication(new EmailServiceImpl());
    }
}
