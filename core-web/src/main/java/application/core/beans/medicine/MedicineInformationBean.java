package application.core.beans.medicine;

import application.core.model.Medicine;
import application.core.model.MedicineOrder;
import application.core.model.ReplacementGroup;
import org.hibernate.validator.constraints.Length;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean(name = "medicineInformationBean")
@RequestScoped
public class MedicineInformationBean implements Serializable {

    private Long id = null;

    @NotNull(message = "price cannot be empty")
    private BigDecimal price;

    @NotNull
    @Length(min = 1, max = 25, message = "Name cannot be empty or longer than 25 characters.")
    private String name;

    @Length(max = 500, message = "Description cannot be longer than 500 characters.")
    private String description;

    @NotNull(message = "Type must be set to 'bought' or 'created'")
    @Pattern(regexp = "bought|produced", message = "Type must be set to bought or produced")
    private String type;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 0, message = "quantity must be greater than or equal to 0")
    private Long quantity;

    @NotNull(message = "Minimum quantity cannot be empty")
    @Min(value = 0, message = "Minimum quantity must be greater than or equal to 0")
    private Long minQuantity;


    @NotNull(message = "Maximum quantity cannot be empty")
    @Min(value = 0, message = "Maximum quantity must be greater than or equal to 0")
    private Long maxQuantity;


    public MedicineInformationBean() {
    }

    private List<MedicineOrder> orders;

    private ReplacementGroup replacementGroup;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReplacementGroup getReplacementGroup() {
        return replacementGroup;
    }

    public void setReplacementGroup(ReplacementGroup replacementGroup) {
        this.replacementGroup = replacementGroup;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<MedicineOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MedicineOrder> orders) {
        this.orders = orders;
    }

    public Long getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
