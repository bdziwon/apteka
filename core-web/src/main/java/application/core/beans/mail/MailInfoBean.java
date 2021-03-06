package application.core.beans.mail;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;

/**
 * Bean to hold username and password when logging in
 * or registering
 */
@ManagedBean(name = "mailInfoBean")
@RequestScoped

public class MailInfoBean implements Serializable {


    @NotNull
    private String text;
   @NotNull
   private File file;



    public MailInfoBean() {

    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
