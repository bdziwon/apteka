package application.core.beans.replacement;

import application.core.model.MedicineOrder;
import application.core.model.ReplacementGroup;
import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean(name = "replacementGroupInformationBean")
@RequestScoped
public class ReplacementGroupInformationBean implements Serializable {

    private Long id = null;

    @NotNull
    @Length(min = 1, max = 25, message = "Name cannot be empty or longer than 25 characters.")
    private String name;

    public ReplacementGroupInformationBean() {
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
}
