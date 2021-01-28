public class MyApplication {

    private EmailService email;

    public MyApplication(EmailService email) {
        this.email = email;
    }

    public void processMessages(String msg, String rec){
        //do some msg validation, manipulation logic etc
        email.sendEmail(msg, rec);
    }

}
