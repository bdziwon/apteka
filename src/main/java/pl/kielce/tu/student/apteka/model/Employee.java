package pl.kielce.tu.student.apteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @NotNull
    @Column(name = "first_name", length = 35)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 35)
    private String lastName;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @NotNull
    private List<Recipe> recipes;

    @Override
    public Long getId() {
        return null;
    }
}
