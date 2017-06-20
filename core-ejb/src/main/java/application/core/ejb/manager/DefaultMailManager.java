package application.core.ejb.manager;

import application.core.api.manager.MailManager;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.Subject;

@Stateless
public class DefaultMailManager implements MailManager {

        final String username = "paiprojekt2017@gmail.com";
        final String password = "hubert123";
    @Override
    public void sendMail(String text) {
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
            message.setText(text);

            Transport.send(message);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
