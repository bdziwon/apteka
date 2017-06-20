package application.core.beans.autentication;

import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Bartek on 18.06.2017.
 */

@ManagedBean(name = "registerInformationBean")
@RequestScoped

public class RegisterInformationBean implements Serializable {

    @NotNull
    @Length(min = 1, max = 20, message = "First Name cannot be empty or longer than 20 characters.")
    private String firstName;

    @NotNull
    @Length(min = 1, max = 20, message = "Last Name cannot be empty or longer than 20 characters.")
    private String lastName;

    public RegisterInformationBean() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
