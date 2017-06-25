package application.core.api.manager;

import javax.ejb.Remote;
import java.io.File;

@Remote
public interface MailManager {
    void sendMail(String text,File file);
}
