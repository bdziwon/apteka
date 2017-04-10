package pl.kielce.tu.student.apteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "pharmacy")
public class Pharmacy implements Persistable<Long> {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Pharmacy() {
    }

    @Override
    public Long getId() {
        return id;
    }
}
