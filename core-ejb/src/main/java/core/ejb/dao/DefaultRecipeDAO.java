package core.ejb.dao;
import core.api.dao.RecipeDAO;
import core.model.Recipe;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Hubert on 2017-05-28.
 */
@Stateless
public class DefaultRecipeDAO implements RecipeDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Recipe findRecipe(Long id) {
        return entityManager.find(Recipe.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Recipe> findallRecipes() {
        return entityManager.createNamedQuery("Recipe.findAllOrdered").getResultList();
    }

    @Override
    public Recipe mergeRecipe(Recipe recipe) {
        return entityManager.merge(recipe);
    }

    @Override
    public void persistRecipe(Recipe recipe) {
        entityManager.persist(recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        entityManager.remove(recipe);
    }

}
