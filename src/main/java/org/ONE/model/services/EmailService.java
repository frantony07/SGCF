package org.ONE.model.services;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public interface EmailService {

    void sendEmail(String to, String code, String s);
    String loadTemplate(String code) throws Exception ;

}