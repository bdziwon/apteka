package application.core.api.dao;


import application.core.model.MedicineOrder;

import javax.ejb.Local;
import java.util.List;


@Local
public interface MedicineOrderDAO {

    void removeMedicineOrder(MedicineOrder medicineOrder);

    void persistMedicineOrder(MedicineOrder medicineOrder);

    MedicineOrder mergeMedicineOrder(MedicineOrder medicineOrder);

    MedicineOrder findMedicineOrder(Long id);

    List<MedicineOrder> findallMedicineOrders();

}