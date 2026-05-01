package org.ONE.services;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EmailService {

    private final String from = "frantonynieves@gmail.com";
    private final String password = "znhc akoh zlwu khas";

    public void sendEmail(String to, String code) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Recuperação de Senha");

            String html = loadTemplate(code);

            message.setContent(html, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String loadTemplate(String code) throws Exception {

        InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("Email.html");

        String html = new String(input.readAllBytes(), StandardCharsets.UTF_8);

        return html.replace("{{CODE}}", code);
    }
}