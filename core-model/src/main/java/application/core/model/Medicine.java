package application.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long    id;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "name", length = 25)
    private String  name;

    @NotNull
    @Column(name = "description", length = 500)
    private String  description;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_replacements")
    private ReplacementGroup replacementGroup;

    @NotNull
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<MedicineOrder> orders;

    @NotNull
    @Column(name = "type")
    private String type = "bought";

    @NotNull
    @Column(name = "quantity")
    private Long quantity = 0L;

    public Medicine() {
        super();
    }

    public Medicine(BigDecimal price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReplacementGroup getReplacementGroup() {
        return replacementGroup;
    }

    public void setReplacementGroup(ReplacementGroup replacementGroup) {
        setReplacementGroup(replacementGroup);
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
}
