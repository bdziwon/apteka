package application.core.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "MedicineOrder.findAllOrdered",
                query = "SELECT medicineOrder " +
                        "FROM MedicineOrder medicineOrder " +
                        "ORDER BY medicineOrder.id ASC"
        )
})

@Entity
@Table(name = "medicine_order")
public class MedicineOrder implements Serializable {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Min(0)
    @NotNull
    @Column(name = "quantity")
    private Long quantity = 0L;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_medicine")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    public MedicineOrder() {
    }

    public MedicineOrder(Long quantity, Medicine medicine, Recipe recipe) {
        this.quantity = quantity;
        this.medicine = medicine;
        this.recipe = recipe;
    }

    public MedicineOrder(Long quantity, Medicine medicine) {
        this.quantity = quantity;
        this.medicine = medicine;
    }

    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        MedicineOrder that = (MedicineOrder) (obj);
        if (that == this) {
            return true;
        }
        return this.id.equals(that.getId());
    }
}
