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
@ManagedBean(name = "credentialsBean")
@RequestScoped

public class CredentialsBean implements Serializable {

    @NotNull
    @Length(min = 3, max = 19, message = "Username should be longer than 2 chars and shorter than 20 chars")
    private String username;

    @NotNull
    @Length(min = 6, max = 24, message = "Password should be longer than 5 chars and shorter than 25 chars")
    private String password;

    public CredentialsBean(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
