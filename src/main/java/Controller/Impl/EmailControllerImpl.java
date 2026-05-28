package Controller.Impl;

import Controller.EmailController;
import jakarta.mail.*;
import org.ONE.model.services.EmailService;
import org.ONE.model.services.impl.EmailServiceImpl;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class EmailControllerImpl implements EmailController {
    EmailService emailService = new EmailServiceImpl();
    public EmailControllerImpl() {
    }



    @Override
    public void sendEmail(String to, String code, String s) {
        emailService.sendEmail(to,code,s);
    }

    @Override
    public String loadTemplate(String code) throws Exception {

        return emailService.loadTemplate(code);
    }
}
