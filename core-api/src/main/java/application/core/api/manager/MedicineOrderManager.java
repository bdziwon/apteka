
package application.core.api.manager;
import application.core.api.exception.MedicineOrderNotFoundException;
import application.core.model.MedicineOrder;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MedicineOrderManager {
    List<MedicineOrder> findallMedicineOrders();

    MedicineOrder findMedicineOrder(Long id) throws MedicineOrderNotFoundException;

    MedicineOrder mergeMedicineOrder(MedicineOrder medicineOrder);

    void persistMedicineOrder(MedicineOrder medicineOrder);

    void removeMedicineOrder(MedicineOrder medicineOrder);
}
