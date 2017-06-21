package application.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Recipe.findAllOrdered",
                query = "SELECT recipe " +
                        "FROM Recipe recipe"
        ),
        @NamedQuery(
                name = "Recipe.findByEmployee",
                query = "SELECT  recipe " +
                        "FROM Recipe recipe " +
                        "WHERE recipe.employee.id = :employee_id " +
                        "ORDER BY recipe.id ASC"
        )
})

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<MedicineOrder> medicines;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MedicineOrder> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineOrder> medicines) {
        this.medicines = medicines;
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
}
