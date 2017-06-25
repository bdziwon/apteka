package dao;

import application.core.api.dao.MedicineDAO;
import application.core.ejb.dao.DefaultMedicineDAO;
import application.core.model.Medicine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class MedicineDAOTest {

    private MedicineDAO medicineDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        medicineDAO = new DefaultMedicineDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultMedicineDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(medicineDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllMedicines() {
        entityManager.getTransaction().begin();
        Medicine medicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);

        medicineDAO.persistMedicine(medicine);
        medicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        medicineDAO.persistMedicine(medicine);
        int size = medicineDAO.findallMedicines().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveMedicine() {
        entityManager.getTransaction().begin();
        Medicine medicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        medicine = medicineDAO.mergeMedicine(medicine);
        medicineDAO.removeMedicine(medicine);
        int size = medicineDAO.findallMedicines().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }

    @Test
    public void shouldFindMedicineById() {
        entityManager.getTransaction().begin();
        Medicine expectedMedicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        expectedMedicine = medicineDAO.mergeMedicine(expectedMedicine);
        Medicine actualMedicine = medicineDAO.findMedicine(expectedMedicine.getId());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedMedicine, actualMedicine);
    }

    @Test
    public void shouldFindMedicineByName() {
        entityManager.getTransaction().begin();
        Medicine expectedMedicine = new Medicine(new BigDecimal(1),"name","desc","bought",
                20L, 10L, 20L);
        expectedMedicine = medicineDAO.mergeMedicine(expectedMedicine);
        Medicine actualMedicine = medicineDAO.findMedicineByName(expectedMedicine.getName());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedMedicine, actualMedicine);
    }
}


