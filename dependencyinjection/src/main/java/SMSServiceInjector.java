public class SMSServiceInjector implements MessageServiceInjector {

    public Consumer getConsumer() {
        return new MyDIApplication(new SMSServiceImpl());
    }
}
