package smart.BackEnd;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;



public class SendEmail {
    public SendEmail() {
    }

    public static final String WELCOME_EMAIL="welcomeEmail";
    private String subject = "Welcome To Smart Pharmacy";

    public SendEmail SendEmailFROMTO(String to, String Subject, String Messgae, String attachmentPath, String attachmentName) {

        String from = "aishaalharweel@gmail.com";
        String password = "Faraghannam@66";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));


            message.setSubject(Subject);


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(Messgae, "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            if (!attachmentPath.isEmpty()) {
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = attachmentPath;
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(attachmentName);
                multipart.addBodyPart(messageBodyPart);
            }
            // Send the complete message parts
            message.setContent(multipart, "text/html; charset=utf-8");


            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return this;
    }


}