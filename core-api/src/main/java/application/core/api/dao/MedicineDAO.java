package application.core.api.dao;


import application.core.model.Medicine;

import java.util.List;

import javax.ejb.Local;


@Local
public interface MedicineDAO {

    void removeMedicine(Medicine medicine);

    void persistMedicine(Medicine medicine);


    Medicine mergeMedicine(Medicine medicine);

    Medicine findMedicine(Long id);

    List<Medicine> findallMedicines();

}