package com.ftn.service.implementation;

import com.ftn.service.EmailService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import org.springframework.stereotype.Service;

/**
 * Created by zlatan on 1/15/18.
 */
@Service
public class EmailServiceImpl implements EmailService {

    final String username = "zlajox@gmail.com";
    final String password = "pass";
/*
    @Override
    public void sendEmail(String to) throws MessagingException, IOException {

        Properties props = new Properties();
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.EnableSSL.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("bla");
            message.setText("bodi");
            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<html>Pdf test :)</html>", "text/html");
            DataSource source = new FileDataSource("/Users/zlatan/Downloads/Predlogprojekta.pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("UmbreloPDF.pdf");
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception e) {

        }
    }
*/
}
