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

    private String subject = "Welcome To Smart Pharmacy";

    public void sendGreetingEmail(String to, String name, String ID) {

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


            message.setSubject("Welcome to Smart Pharmacy Project");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<h1><strong>Welcome "+ name +"</strong></h1>\n" +
                    "<p>You have signed up successfully for smart pharmacy account</p>\n" +
                    "<p><strong>your account ID : "+ ID +"</strong></p>";

            // Now set the actual message
            messageBodyPart.setContent(Messgae, "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            String attachmentPath="";
            String attachmentName="";
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
    }

    public void sendSessionEmail(String to,String patientName, String doctorName, String ts,String medicalCondition,String notes, String drugs) {

        String from = "aishaalharweel@gmail.com";
        String password = "Aishaghannam@69";

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


            message.setSubject("[Smart Pharmacy] New Session added to your account");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<h3>Dear "+ patientName+",</h3>\n" +
                    "<p><strong>Session Details:</strong></p>\n" +
                    "<p><span style=\"color: #ff0000;\">Session Time:</span> "+ ts+"<br /><span style=\"color: #ff0000;\">Doctor Name:</span> "+doctorName+"<br /><span style=\"color: #ff0000;\">Medical Condition:</span><br />"+medicalCondition+"<br /><span style=\"color: #ff0000;\">Doctor Notes:</span><br />"+notes+"<br /><br /><span style=\"color: #ff0000;\">Prescriped Drugs:</span><br />"+drugs+"</p>";

            // Now set the actual message
            messageBodyPart.setContent(Messgae, "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            String attachmentPath="";
            String attachmentName="";
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
    }

    public void sendEmail(String to, String Subject, String Messgae, String attachmentPath, String attachmentName) {

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
    }


}