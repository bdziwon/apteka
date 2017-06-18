package application.core.api.dao;


import application.core.model.MedicineOrder;

import java.util.List;

import javax.ejb.Local;


@Local
public interface MedicineOrderDAO {

    void removeMedicineOrder(MedicineOrder medicineOrder);

    void persistMedicineOrder(MedicineOrder medicineOrder);

    MedicineOrder mergeMedicineOrder(MedicineOrder medicineOrder);

    MedicineOrder findMedicineOrder(Long id);

    List<MedicineOrder> findallMedicineOrders();

}