package application.core.beans.recipe;

import application.core.model.Client;
import application.core.model.Employee;
import application.core.model.Medicine;
import application.core.model.MedicineOrder;
import application.core.session.SessionUtils;
import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name = "recipeInformationBean")
@ViewScoped
public class RecipeInformationBean implements Serializable {

    private Long id = null;

    @NotNull(message = "Can't add recipe, please choose client from list.")
    private Client client;

    @NotNull(message = "Authorization failed.")
    private Employee employee = SessionUtils.getEmployee();

    @NotNull
    private List<MedicineOrder> medicines;

    @NotNull(message = "Select medicine to add.")
    private Medicine selectedMedicine;

    @NotNull(message = "Fill quantity field.")
    @Min(value = 1, message = "quantity must be greater than or equal to 1")
    private Long selectedQuantity;

    public RecipeInformationBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<MedicineOrder> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineOrder> medicines) {
        this.medicines = medicines;
    }

    public Medicine getSelectedMedicine() {
        return selectedMedicine;
    }

    public void setSelectedMedicine(Medicine selectedMedicine) {
        this.selectedMedicine = selectedMedicine;
    }

    public Long getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(Long selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
