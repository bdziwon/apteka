package application.core.api.manager;

import java.util.List;


import application.core.api.exception.MedicineNotFoundException;
import application.core.model.Medicine;


import javax.ejb.Remote;

@Remote
public interface MedicineManager {
    List<Medicine> findAllMedicines();

    Medicine findMedicine(Long id) throws MedicineNotFoundException;

    Medicine mergeMedicine(Medicine medicine);

    void persistMedicine(Medicine medicine);

    void removeMedicine(Medicine medicine);

    Medicine findMedicineByName(String name) throws MedicineNotFoundException;
}
