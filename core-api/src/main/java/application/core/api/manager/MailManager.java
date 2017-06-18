package application.core.api.manager;

import javax.ejb.Remote;

/**
 * Created by Hubert on 2017-06-18.
 */
@Remote
public interface MailManager {


    void sendMail(String recipents,String subject, String text);
}
