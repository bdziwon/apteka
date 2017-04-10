package pl.kielce.tu.student.apteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "first_name", length = 35)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 35)
    private String lastName;

    @NotNull
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
