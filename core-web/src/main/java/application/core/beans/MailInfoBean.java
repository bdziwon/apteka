package application.core.beans;


import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Bean to hold username and password when logging in
 * or registering
 */
@ManagedBean(name = "mailInfoBean")
@RequestScoped

public class MailInfoBean implements Serializable {

    @NotNull

    private String recipents;

    @NotNull

    private String Subject;
    @NotNull

    private String text;

    public MailInfoBean(){

    }

    public String getRecipents() {
        return recipents;
    }

    public String getSubject() {
        return Subject;
    }

    public String getText() {
        return text;
    }

    public void setRecipents(String recipents) {
        this.recipents = recipents;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }
}
