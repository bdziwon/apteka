package application.core.ejb.manager;


import application.core.api.dao.MedicineOrderDAO;
import application.core.api.exception.MedicineOrderNotFoundException;
import application.core.api.manager.MedicineOrderManager;
import application.core.model.MedicineOrder;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
@Stateless

public class DefaultMedicineOrderManager implements MedicineOrderManager {
    @EJB
    private MedicineOrderDAO medicineOrderDAO;
    @Override
    public void removeMedicineOrder(MedicineOrder medicineOrder) {
        medicineOrderDAO.removeMedicineOrder(medicineOrder);
    }

    @Override
    public void persistMedicineOrder(MedicineOrder medicineOrder) {
        medicineOrderDAO.persistMedicineOrder(medicineOrder);
    }

    @Override
    public MedicineOrder mergeMedicineOrder(MedicineOrder medicineOrder) {
        return medicineOrderDAO.mergeMedicineOrder(medicineOrder);
    }

    @Override
    public MedicineOrder findMedicineOrder(Long id) throws MedicineOrderNotFoundException {
        MedicineOrder medicineOrder = medicineOrderDAO.findMedicineOrder(id);
        if (medicineOrder == null)
            throw new MedicineOrderNotFoundException();
        return medicineOrder;
    }

    @Override
    public List<MedicineOrder> findallMedicineOrders() {
        return medicineOrderDAO.findallMedicineOrders();
    }

}

