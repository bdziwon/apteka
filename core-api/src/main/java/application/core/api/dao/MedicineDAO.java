package application.core.api.dao;


import application.core.model.Medicine;

import javax.ejb.Local;
import java.util.List;


@Local
public interface MedicineDAO {

    void removeMedicine(Medicine medicine);

    void persistMedicine(Medicine medicine);

    Medicine mergeMedicine(Medicine medicine);

    Medicine findMedicine(Long id);

    List<Medicine> findallMedicines();

    Medicine findMedicineByName(String name);
}