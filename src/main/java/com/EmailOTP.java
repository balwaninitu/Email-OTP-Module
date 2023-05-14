package com;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailOTP {
	
	// Generate a random 6-digit OTP
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return Integer.toString(otp);
    }
    
    // Send the OTP to the client email
      public static void sendOTPEmail(String email, String otp, String smtpPassword) throws MessagingException {
    	String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        
     // Set the SMTP 
      Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        
        
     // Create a new SMTP session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, smtpPassword);
            }
        });
        
     // Create a new email message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is " + otp + ".");

        // Send the email
        Transport.send(message);

    }

}
