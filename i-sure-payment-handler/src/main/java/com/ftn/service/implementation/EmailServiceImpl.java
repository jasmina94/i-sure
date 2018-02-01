package com.ftn.service.implementation;

import com.ftn.service.EmailService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zlatan on 1/15/18.
 */
@Service
public class EmailServiceImpl implements EmailService {

    final String username = "bsepprojectimpl@gmail.com";
    final String password = "MasterDigree";

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.emails}")
    private String dc_emails;

    @Override
    public void sendEmail(String to, String attachmentName) throws MessagingException, IOException {

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
            message.setSubject("ISure- Your insurance policy");
            message.setText("Pdf");

            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<html><p><font size='4'><b>Dear customer</b>,<br/><br/> In attachment is your policy. This is confirmation of your purchase. <br/> Thank you for your trust, <br/><br/> Sincerely ISure team.</font></p></html>", "text/html");

            String path = "src/main/resources/policy/" + attachmentName;
            DataSource source = new FileDataSource(path);

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void sendEmails(List<String> salesmans, String attachmentName) throws MessagingException, IOException {
        System.out.println("Sending mails to salesmans");

        String senderUsername = "info@isure.com";
        String senderPassword = "00000";


        Properties props = new Properties();
        props.put("mail.smtp.user", senderUsername);
        props.put("mail.smtp.host", "smtp.isure.com");
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
                        return new PasswordAuthentication(senderUsername, senderPassword);
                    }
                });
        try {
            for (String salesman : salesmans) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderUsername));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(salesman));
                message.setSubject("ISure- Sold insurance policy");
                message.setText("Pdf");

                BodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                BodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent("<html><p><font size='4'><b>Dear salesman</b>,<br/><br/> In attachment is policy you selled. This is confirmation purchase. <br/><br/> Sincerely ISure team.</font></p></html>", "text/html");

                String path = "src/main/resources/policy/" + attachmentName;
                DataSource source = new FileDataSource(path);

                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(attachmentName);
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(htmlPart);
                message.setContent(multipart);
                Transport.send(message);
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public List<String> getSalesmanEmail() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = dc_home + dc_emails;
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(uri, String[].class);
        List<String> emails = (Arrays.asList(responseEntity.getBody()));
        return emails;
    }
}
