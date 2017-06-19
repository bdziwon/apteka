package application.core.ejb.manager;

import application.core.api.manager.MailManager;

import javax.ejb.Stateless;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.Subject;

/**
 * Created by Hubert on 2017-06-18.
 */
@Stateless
public class DefaultMailManager implements MailManager {

        final String username = "paiprojekt2017@gmail.com";
        final String password = "hubert123";
    @Override
    public void sendMail(String recipents, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipents));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
