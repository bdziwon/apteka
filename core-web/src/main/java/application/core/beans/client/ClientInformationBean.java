package application.core.beans.client
        ;

import application.core.model.MedicineOrder;
import application.core.model.ReplacementGroup;
import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean(name = "clientInformationBean")
@RequestScoped
public class ClientInformationBean implements Serializable {

    private Long id = null;

    @NotNull
    @Length(min = 1, max = 25, message = "Name cannot be empty or longer than 25 characters.")
    private String name;
    private String pesel;
    private String lastname;
    private  String phoneNumber;


    public ClientInformationBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
