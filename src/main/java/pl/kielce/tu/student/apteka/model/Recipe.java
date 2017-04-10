package pl.kielce.tu.student.apteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Bartek on 2017-04-10.
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Persistable<Long>{

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Recipe() {

    }


    @Override
    public Long getId() {
        return null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
