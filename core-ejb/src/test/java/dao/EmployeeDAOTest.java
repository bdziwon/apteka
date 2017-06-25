package dao;

import application.core.api.dao.EmployeeDAO;
import application.core.ejb.dao.DefaultEmployeeDAO;
import application.core.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class EmployeeDAOTest {

    private EmployeeDAO employeeDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        employeeDAO = new DefaultEmployeeDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultEmployeeDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(employeeDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllEmployees() {
        entityManager.getTransaction().begin();
        Employee employee = new Employee("firstName", "lastName", "username", "password");
        employeeDAO.persistEmployee(employee);
        employee = new Employee("firstName", "lastName", "username", "password");
        employeeDAO.persistEmployee(employee);
        int size = employeeDAO.findAllEmployees().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveEmployee() {
        entityManager.getTransaction().begin();
        Employee employee = new Employee("firstName", "lastName", "username", "password");
        employee = employeeDAO.mergeEmployee(employee);
        employeeDAO.removeEmployee(employee);
        int size = employeeDAO.findAllEmployees().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }

    @Test
    public void shouldFindEmployeeById() {
        entityManager.getTransaction().begin();
        Employee expectedEmployee = new Employee("firstName", "lastName", "username", "password");
        expectedEmployee = employeeDAO.mergeEmployee(expectedEmployee);
        Employee actualEmployee = employeeDAO.findEmployee(expectedEmployee.getId());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void shouldFindEmployeeByUsername() {
        entityManager.getTransaction().begin();
        Employee expectedEmployee = new Employee("firstName", "lastName", "username", "password");
        expectedEmployee = employeeDAO.mergeEmployee(expectedEmployee);
        Employee actualEmployee = employeeDAO.findAllEmployeesByUsername(expectedEmployee.getUsername()).get(0);
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void shouldFindEmployeeByCredentials() {
        entityManager.getTransaction().begin();
        Employee expectedEmployee = new Employee("firstName", "lastName", "username", "password");
        expectedEmployee = employeeDAO.mergeEmployee(expectedEmployee);
        Employee actualEmployee = employeeDAO.findEmployeesByCredentials(expectedEmployee.getUsername(), expectedEmployee.getPassword()).get(0);
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

}


