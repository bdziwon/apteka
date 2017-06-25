package dao;

import application.core.api.dao.ReplacementGroupDAO;
import application.core.ejb.dao.DefaultReplecementGroupDAO;
import application.core.model.ReplacementGroup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class ReplacementGroupDAOTest {

    private ReplacementGroupDAO replacementGroupDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        replacementGroupDAO = new DefaultReplecementGroupDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultReplecementGroupDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(replacementGroupDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllReplacementGroups() {
        entityManager.getTransaction().begin();
        ReplacementGroup replacementGroup = new ReplacementGroup("name");
        replacementGroupDAO.persistReplacementGroup(replacementGroup);
        replacementGroup = new ReplacementGroup("name");
        replacementGroupDAO.persistReplacementGroup(replacementGroup);
        int size = replacementGroupDAO.findallReplacementGroups().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveReplacementGroup() {
        entityManager.getTransaction().begin();
        ReplacementGroup replacementGroup = new ReplacementGroup("name");
        replacementGroup = replacementGroupDAO.mergeReplacementGroup(replacementGroup);
        replacementGroupDAO.removeReplacementGroup(replacementGroup);
        int size = replacementGroupDAO.findallReplacementGroups().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }

    @Test
    public void shouldFindReplacementGroupById() {
        entityManager.getTransaction().begin();
        ReplacementGroup expectedReplacementGroup = new ReplacementGroup("name");
        expectedReplacementGroup = replacementGroupDAO.mergeReplacementGroup(expectedReplacementGroup);
        ReplacementGroup actualReplacementGroup = replacementGroupDAO.findReplacementGroup(expectedReplacementGroup.getId());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedReplacementGroup, actualReplacementGroup);
    }

    @Test
    public void shouldFindReplacementGroupByName() {
        entityManager.getTransaction().begin();
        ReplacementGroup expectedReplacementGroup = new ReplacementGroup("name");
        expectedReplacementGroup = replacementGroupDAO.mergeReplacementGroup(expectedReplacementGroup);
        ReplacementGroup actualReplacementGroup = replacementGroupDAO.findReplacementByName(expectedReplacementGroup.getName());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedReplacementGroup, actualReplacementGroup);
    }
}


