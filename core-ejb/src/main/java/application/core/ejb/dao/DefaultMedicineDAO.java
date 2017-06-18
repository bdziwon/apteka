package application.core.ejb.dao;
import application.core.api.dao.MedicineDAO;
import application.core.model.Medicine;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Hubert on 2017-05-28.
 */
@Stateless
public class DefaultMedicineDAO implements MedicineDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Medicine findMedicine(Long id) {
        return entityManager.find(Medicine.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Medicine> findallMedicines() {
        return entityManager.createNamedQuery("Medicine.findAllOrdered").getResultList();
    }

    @Override
    public Medicine mergeMedicine(Medicine medicine) {
        return entityManager.merge(medicine);
    }

    @Override
    public void persistMedicine(Medicine medicine) {
        entityManager.persist(medicine);
    }

    @Override
    public void removeMedicine(Medicine medicine) {
        entityManager.remove(entityManager.merge(medicine));
    }

}