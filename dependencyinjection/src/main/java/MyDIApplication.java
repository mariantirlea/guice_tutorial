public class MyDIApplication implements Consumer {

    private MessageService service;

    public void setService(MessageService service){
        this.service = service;
    }

    public void processMessages(String msg, String rec) {
        //do some msg validation, manipulation logic etc
        service.sendMessage(msg, rec);
    }
}
