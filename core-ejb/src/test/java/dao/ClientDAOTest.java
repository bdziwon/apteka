package dao;

import application.core.api.dao.ClientDAO;
import application.core.ejb.dao.DefaultClientDAO;
import application.core.model.Client;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class ClientDAOTest {

    private ClientDAO clientDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        clientDAO = new DefaultClientDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultClientDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(clientDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllClients() {
        entityManager.getTransaction().begin();
        Client client = new Client("name","lastName", "888888888", 848882L);
        clientDAO.persistClient(client);
        client = new Client("name","lastName", "888888888", 848882L);
        clientDAO.persistClient(client);
        int size = clientDAO.findallClients().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveClient() {
        entityManager.getTransaction().begin();
        Client client = new Client("name","lastName", "888888888", 848882L);
        client = clientDAO.mergeClient(client);
        clientDAO.removeClient(client);
        int size = clientDAO.findallClients().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }

    @Test
    public void shouldFindClientById() {
        entityManager.getTransaction().begin();
        Client expectedClient = new Client("name","lastName", "888888888", 848882L);
        expectedClient = clientDAO.mergeClient(expectedClient);
        Client actualClient = clientDAO.findClient(expectedClient.getId());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedClient, actualClient);
    }
}


