package core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "replacement_group")
public class ReplacementGroup {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
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
}
