package application.core.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Medicine.findAllOrdered",
                query = "SELECT medicine " +
                        "FROM Medicine medicine " +
                        "ORDER BY medicine.id ASC"
        ),
        @NamedQuery(
                name = "Medicine.findByName",
                query = "SELECT medicine " +
                        "FROM Medicine medicine " +
                        "WHERE medicine.name = :name " +
                        "ORDER BY medicine.id ASC"
        )


})


@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Price cannot be empty")
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Name cannot be empty or longer than 25 characters")
    @Column(name = "name", length = 25)
    private String name;

    @NotNull(message = "Description cannot be empty or longer than 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_replacements")
    private ReplacementGroup replacementGroup;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<MedicineOrder> orders;

    @NotNull(message = "Type cannot be empty")
    @Column(name = "type")
    @Pattern(regexp = "bought|produced", message = "Error: Unsupported type value")
    private String type = "bought";

    @NotNull(message = "Quantity cannot be empty")
    @Column(name = "quantity")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Long quantity = 0L;

    @NotNull(message = "Minimum quantity cannot be empty")
    @Column(name = "min_quantity")
    @Min(value = 0, message = "Minimum quantity must be greater than or equal to 0")
    private Long minQuantity = 0L;

    @NotNull(message = "Maximum quantity cannot be empty")
    @Column(name = "max_quantity")
    @Min(value = 0, message = "Maxiumum quantity must be greater than or equal to 0")

    private Long maxQuantity = 0L;


    public Medicine() {
        super();
    }

    public Medicine(BigDecimal price, String name, String description, String type, Long quantity, Long minQuantity, Long maxQuantity) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public Medicine(BigDecimal price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," +
                name + ",";


    }

    public ReplacementGroup getReplacementGroup() {
        return replacementGroup;
    }

    public void setReplacementGroup(ReplacementGroup replacementGroup) {
        this.replacementGroup = replacementGroup;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object obj) {
        Medicine that = (Medicine) obj;
        return that.id.equals(this.getId());
    }
}


