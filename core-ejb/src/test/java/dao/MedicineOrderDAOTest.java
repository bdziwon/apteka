package dao;

import application.core.api.dao.MedicineOrderDAO;
import application.core.ejb.dao.DefaultMedicineOrderDAO;
import application.core.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class MedicineOrderDAOTest {

    private MedicineOrderDAO medicineOrderDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        medicineOrderDAO = new DefaultMedicineOrderDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultMedicineOrderDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(medicineOrderDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllMedicineOrders() {
        entityManager.getTransaction().begin();
        Medicine medicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        medicine = entityManager.merge(medicine);
        MedicineOrder medicineOrder = new MedicineOrder(10L, medicine);
        medicineOrderDAO.persistMedicineOrder(medicineOrder);
        medicineOrder = new MedicineOrder(10L, medicine);
        medicineOrderDAO.persistMedicineOrder(medicineOrder);
        int size = medicineOrderDAO.findallMedicineOrders().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveMedicineOrder() {
        entityManager.getTransaction().begin();
        Medicine medicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        medicine = entityManager.merge(medicine);
        MedicineOrder medicineOrder = new MedicineOrder(10L, medicine);
        medicineOrder = medicineOrderDAO.mergeMedicineOrder(medicineOrder);

        System.out.println("Merged id = "+medicineOrder.getId());
        medicineOrderDAO.removeMedicineOrder(medicineOrder);
        int size = medicineOrderDAO.findallMedicineOrders().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }
}


