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

    private String text;

    public MailInfoBean(){

    }


    public String getText() {
        return text;
    }



    public void setText(String text) {
        this.text = text;
    }
}
