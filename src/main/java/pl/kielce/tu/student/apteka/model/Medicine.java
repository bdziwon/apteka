package pl.kielce.tu.student.apteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bartek on 2017-04-08.
 */

@Entity
@Table(name = "medicine")
public class Medicine implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long    id;

    @NotNull
    @Column(name = "price")
    private double  price;

    @NotNull
    @Column(name = "name", length = 25)
    private String  name;

    @NotNull
    @Column(name = "description", length = 500)
    private String  description;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Medicine() {
        super();
    }

    public Medicine(Long id, Long price, String name, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
