package application.core.ejb.dao;

import application.core.api.dao.MedicineOrderDAO;
import application.core.model.MedicineOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DefaultMedcineOrderDAO implements MedicineOrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MedicineOrder findMedicineOrder(Long id) {
        return entityManager.find(MedicineOrder.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MedicineOrder> findallMedicineOrders() {
        return entityManager.createNamedQuery("MedicineOrder.findAllOrdered").getResultList();
    }

    @Override
    public MedicineOrder mergeMedicineOrder(MedicineOrder medicineOrder) {
        return entityManager.merge(medicineOrder);
    }

    @Override
    public void persistMedicineOrder(MedicineOrder medicineOrder) {
        entityManager.persist(medicineOrder);
    }

    @Override
    public void removeMedicineOrder(MedicineOrder medicineOrder) {
        entityManager.remove(entityManager.merge(medicineOrder));
    }

}
