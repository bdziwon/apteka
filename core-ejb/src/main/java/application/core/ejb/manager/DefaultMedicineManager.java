package application.core.ejb.manager;


import application.core.api.dao.MedicineDAO;
import application.core.api.exception.MedicineNotFoundException;
import application.core.api.manager.MedicineManager;
import application.core.model.Medicine;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
@Stateless

public class DefaultMedicineManager implements MedicineManager {
    @EJB
    private MedicineDAO medicineDAO;

    @Override
    public void removeMedicine(Medicine medicine) {
        medicineDAO.removeMedicine(medicine);
    }

    @Override
    public Medicine findMedicineByName(String name) throws MedicineNotFoundException {
        Medicine medicine = medicineDAO.findMedicineByName(name);
        if (medicine == null) {
            throw new MedicineNotFoundException();
        }
        return medicine;
    }

    @Override
    public void persistMedicine(Medicine medicine) {
        medicineDAO.persistMedicine(medicine);
    }

    @Override
    public Medicine mergeMedicine(Medicine medicine) {
        return medicineDAO.mergeMedicine(medicine);
    }

    @Override
    public Medicine findMedicine(Long id) throws MedicineNotFoundException {
        Medicine medicine= medicineDAO.findMedicine(id);
        if (medicine == null)
            throw new MedicineNotFoundException();
        return medicine;
    }

    @Override
    public List<Medicine> findAllMedicines() {
        return medicineDAO.findallMedicines();
    }

}

