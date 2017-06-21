package application.core.model;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Employee.findAllOrdered",
                query = "SELECT employee " +
                        "FROM Employee employee " +
                        "ORDER BY employee.id ASC"
        ),

        @NamedQuery(
                name = "Employee.findByCredentials",
                query = "SELECT  employee " +
                        "FROM Employee employee " +
                        "WHERE employee.username = :username AND employee.password = :password " +
                        "ORDER BY employee.id ASC"
        ),

        @NamedQuery(
                name = "Employee.findByUsername",
                query = "SELECT  employee " +
                        "FROM Employee employee " +
                        "WHERE employee.username = :username " +
                        "ORDER BY employee.id ASC"
        )
})

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

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
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Recipe> addedRecipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public void setAddedRecipes(List<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
