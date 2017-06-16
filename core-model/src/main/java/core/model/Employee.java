package core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "User.findAllOrdered",
                query = "SELECT employee FROM core.model.Employee employee ORDER BY employee.id ASC")
})

@Entity
@Table(name = "employee")
public class Employee {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Recipe> addedRecipes;

    public Long getId() {
        return id;
    }
}
