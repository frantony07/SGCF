package org.ONE.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailServices {

    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 1025;

    public void sendPasswordResetEmail(String toEmail, String token) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        Session session = Session.getInstance(props);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("noreply@sgcf.local"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Recuperação de Senha");
        message.setText(
                "Olá!\n\n" +
                "Seu código de recuperação de senha é: " + token + "\n\n" +
                "Este código é válido por 15 minutos.\n"
        );

        Transport.send(message);
    }
}
