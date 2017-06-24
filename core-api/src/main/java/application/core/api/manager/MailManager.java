package application.core.api.manager;

import javax.ejb.Remote;

@Remote
public interface MailManager {


    void sendMail(String text);
}
