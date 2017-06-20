package application.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "ReplacementGroup.findAllOrdered",
                query = "SELECT replacementGroup " +
                        "FROM ReplacementGroup replacementGroup " +
                        "ORDER BY replacementGroup.id ASC"
        ),
        @NamedQuery(
                name = "ReplacementGroup.findByName",
                query = "SELECT replacementGroup " +
                        "FROM ReplacementGroup replacementGroup " +
                        "WHERE replacementGroup.name = :name " +
                        "ORDER BY replacementGroup.id ASC"
        )
})

@Entity
@Table(name = "replacement_group")
public class ReplacementGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 25)
    private String  name;

    @OneToMany(mappedBy = "replacementGroup", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

    public Long getId() {
        return id;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id+","+name;
    }

    @Override
    public boolean equals(Object obj) {
        ReplacementGroup that = (ReplacementGroup) obj;
        return this.id.equals(that.getId()) && this.name.equals(that.getName());
    }
}
