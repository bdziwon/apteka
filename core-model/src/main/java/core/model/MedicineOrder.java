package core.model;

import javax.validation.constraints.NotNull;


import javax.persistence.*;

@Entity
@Table(name = "medicine_order")
public class MedicineOrder  {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "quantity")
    private Long quantity = 0L;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medicine")
    private Medicine medicine;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    public Long getId() {
        return null;
    }
}
