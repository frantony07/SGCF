package Controller;

public interface EmailController {

    void sendEmail(String to, String code, String s);
    String loadTemplate(String code) throws Exception ;

}
