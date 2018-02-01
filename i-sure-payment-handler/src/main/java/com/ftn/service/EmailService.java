package com.ftn.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by zlatan on 1/15/18.
 */
public interface EmailService {

   void sendEmail(String to, String attachmentName) throws MessagingException, IOException;

   void sendEmails(List<String> salesmans, String attachmentName) throws MessagingException, IOException;

   List<String> getSalesmanEmail();
}
