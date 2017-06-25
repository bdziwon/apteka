package application.core.ejb.manager;

import application.core.api.manager.MailManager;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Stateless
public class DefaultMailManager implements MailManager {

    final String username = "paiprojekt2017@gmail.com";
    final String password = "hubert123";

    @Override
    public void sendMail(String text,File file) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        SimpleDateFormat simpleDateHere = new SimpleDateFormat("dd-MM-yyyy ");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("paiprojekt2017@gmail.com"));
            message.setSubject("zamowienie z dnia" + "" + simpleDateHere.format(new Date()));
            message.setSentDate(new Date());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart textpart = new MimeBodyPart();
            textpart.setText(text);
            multipart.addBodyPart(textpart);
            MimeBodyPart attachPart = new MimeBodyPart();
            try {

                attachPart.attachFile(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);
            message.setContent(multipart);




            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
