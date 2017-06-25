package dao;

import application.core.api.dao.RecipeDAO;
import application.core.ejb.dao.DefaultRecipeDAO;
import application.core.model.Client;
import application.core.model.Employee;
import application.core.model.Recipe;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class RecipeDAOTest {

    private RecipeDAO recipeDAO;
    public EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        recipeDAO = new DefaultRecipeDAO();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPU");
        entityManager = factory.createEntityManager();

        Field field = DefaultRecipeDAO.class.getField("entityManager");
        field.setAccessible(true);
        field.set(recipeDAO, entityManager);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPersistAndFindAllRecipes() {
        entityManager.getTransaction().begin();

        Client client = new Client("name","lastName", "888888888", 848882L);
        client = entityManager.merge(client);
        Employee employee = new Employee("firstName", "lastName", "username", "password");
        employee = entityManager.merge(employee);

        Recipe recipe = new Recipe(client, employee);
        recipeDAO.persistRecipe(recipe);

        recipe = new Recipe(client, employee);
        recipeDAO.persistRecipe(recipe);

        int size = recipeDAO.findallRecipes().size();
        entityManager.getTransaction().commit();

        Assert.assertEquals(2,size);

    }

    @Test
    public void shouldMergeAndRemoveRecipe() {
        entityManager.getTransaction().begin();

        Client client = new Client("name","lastName", "888888888", 848882L);
        client = entityManager.merge(client);
        Employee employee = new Employee("firstName", "lastName", "username", "password");
        employee = entityManager.merge(employee);

        Recipe recipe = new Recipe(client, employee);
        recipe = recipeDAO.mergeRecipe(recipe);
        recipeDAO.removeRecipe(recipe);
        int size = recipeDAO.findallRecipes().size();
        entityManager.getTransaction().commit();
        Assert.assertEquals(0, size);
    }

    @Test
    public void shouldFindRecipeById() {
        entityManager.getTransaction().begin();

        Client client = new Client("name","lastName", "888888888", 848882L);
        client = entityManager.merge(client);
        Employee employee = new Employee("firstName", "lastName", "username", "password");
        employee = entityManager.merge(employee);

        Recipe expectedRecipe = new Recipe(client, employee);
        expectedRecipe = recipeDAO.mergeRecipe(expectedRecipe);
        Recipe actualRecipe = recipeDAO.findRecipe(expectedRecipe.getId());
        entityManager.getTransaction().commit();
        Assert.assertEquals(expectedRecipe, actualRecipe);
    }
}


