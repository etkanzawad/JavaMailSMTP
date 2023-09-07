package javamail;
// Importing the Java Mail Utility
import javax.mail.*;


import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMail {

    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        // Holds key/value pairs of data like a hashmap

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // 2FA disabled and password for gmail account using your own
        String myAccountEmail = "@gmail.com";
        String password = "";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password); // Password parsed to char
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message Sent Successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("First Email");
            message.setText("Hello");
            return message;
        } catch(Exception ex){
            System.out.println("Error");
            //Logger.getLogger(JavaMail.class.getName().log(Level.SEVERE, null, ex));
            // Plugin setup not complete
        }
        return null;
    }
}
