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

    private String from = "iot.safe.pharmacy@gmail.com";
    private String password = "Aishaghannam@69";
    public SendEmail() {
    }

    private String subject = "Welcome To Smart Pharmacy";

    public void sendGreetingEmail(String to, String name, String ID) {

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


            message.setSubject("[IOT Safe Pharmacy] Welcome to IOT Safe Pharmacy Project");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<h1><strong>Welcome "+ name +"</strong></h1>\n" +
                    "<p>You have signed up successfully for smart pharmacy account</p>\n" +
                    "<p><strong>your account ID : "+ ID +"</strong></p>";
            Messgae+="<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p><span style=\"color: #ff0000;\">If you Please use the follwing link to give us feedback<br /><a href=\"https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform\">https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform</a></span></p>\n" +
                    "<p><strong>Note:</strong> If you have any inquieres Please Email us at: <a href=\"mailto:iot.smart.pharmacy@gmail.com\">iot.smart.pharmacy@gmail.com<br /></a>By: Aisha AlHarweel</p>";

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


            message.setSubject("[IOT Safe Pharmacy] New Session added to your account");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<h3>Dear "+ patientName+",</h3>\n" +
                    "<p><strong>Session Details:</strong></p>\n" +
                    "<p><span style=\"color: #ff0000;\">Session Time:</span> "+ ts+"<br /><span style=\"color: #ff0000;\">Doctor Name:</span> "+doctorName+"<br /><span style=\"color: #ff0000;\">Medical Condition:</span><br />"+medicalCondition+"<br /><span style=\"color: #ff0000;\">Doctor Notes:</span><br />"+notes+"<br /><br /><span style=\"color: #ff0000;\">Prescriped Drugs:</span><br />"+drugs+"</p>";

            Messgae+="<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p><span style=\"color: #ff0000;\">If you Please use the follwing link to give us feedback<br /><a href=\"https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform\">https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform</a></span></p>\n" +
                    "<p><strong>Note:</strong> If you have any inquieres Please Email us at: <a href=\"mailto:iot.smart.pharmacy@gmail.com\">iot.smart.pharmacy@gmail.com<br /></a>By: Aisha AlHarweel</p>";


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
            System.out.println("Email Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDrugDispensePermissionEmail(String to, String name, String pharmacyName, String drugDetails, String otp) {

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


            message.setSubject("[IOT Safe Pharmacy] Prescribed Drug Permission");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<p><strong>Dear "+name +"</strong></p>\n" +
                    "<p>Pharmacy: <span style=\"color: #0d7d64;\">"+pharmacyName+"</span> is trying to dispense the following drug from your account:</p>\n" +
                    "<p><span style=\"color: #0d7d64;\">"+drugDetails+"</span></p>\n" +
                    "<p>if you agree please share the OTP with the pharmacy</p>\n" +
                    "<h2 style=\"text-align: center;\"><strong><span style=\"color: #ff0000;\">OTP: "+otp+"</span></strong></h2>\n" +
                    "<p>&nbsp;</p>";


            Messgae+="<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p><span style=\"color: #ff0000;\">If you Please use the follwing link to give us feedback<br /><a href=\"https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform\">https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform</a></span></p>\n" +
                    "<p><strong>Note:</strong> If you have any inquieres Please Email us at: <a href=\"mailto:iot.smart.pharmacy@gmail.com\">iot.smart.pharmacy@gmail.com<br /></a>By: Aisha AlHarweel</p>";

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

    public void sendDrugDispenseInfoEmail(String to, String name, String pharmacyName, String drugDetails,String timeStamp) {

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


            message.setSubject("[IOT Safe Pharmacy] Prescribed Drug Dispensed Successfully");


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();


            String Messgae = "<p><strong>Dear "+name+",</strong></p>\n" +
                    "<p>Thank you for using IOT Safe Pharamcy</p>\n" +
                    "<h3><span style=\"text-decoration: underline; color: #9876aa;\"><strong>Dispense Details:</strong></span></h3>\n" +
                    "<p><span style=\"color: #339966;\">Pharmacy Name: </span>"+pharmacyName+"<br /><span style=\"color: #339966;\">Despensed Drug: </span>"+drugDetails+"<br /><span style=\"color: #339966;\">Timestamp: </span>"+timeStamp+"</p>";


            Messgae+="<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p><span style=\"color: #ff0000;\">If you Please use the follwing link to give us feedback<br /><a href=\"https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform\">https://docs.google.com/forms/d/e/1FAIpQLSd0L_leHAa8OZJpVAHmSBGY7jSz7cCfB7RRfTuXk6YYixOoHg/viewform</a></span></p>\n" +
                    "<p><strong>Note:</strong> If you have any inquieres Please Email us at: <a href=\"mailto:iot.smart.pharmacy@gmail.com\">iot.smart.pharmacy@gmail.com<br /></a>By: Aisha AlHarweel</p>";

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
            System.out.println("Email Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}