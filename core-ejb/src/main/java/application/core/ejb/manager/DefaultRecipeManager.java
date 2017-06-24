package application.core.ejb.manager;



        import application.core.api.dao.RecipeDAO;
        import application.core.api.exception.RecipeNotFoundException;
        import application.core.api.manager.RecipeManager;
        import application.core.model.Employee;
        import application.core.model.Recipe;

        import java.util.List;

        import javax.ejb.EJB;
        import javax.ejb.Stateless;
@Stateless

public class DefaultRecipeManager implements RecipeManager {
    @EJB
    private RecipeDAO recipeDao;
    @Override
    public void removeRecipe(Recipe recipe) {
        recipeDao.removeRecipe(recipe);
    }

    @Override
    public List<Recipe> findRecipesByEmployee(Employee employee) {
        return recipeDao.findRecipesByEmployee(employee);
    }

    @Override
    public void persistRecipe(Recipe recipe) {
        recipeDao.persistRecipe(recipe);
    }

    @Override
    public Recipe mergeRecipe(Recipe recipe) {
        return recipeDao.mergeRecipe(recipe);
    }

    @Override
    public Recipe findRecipe(Long id) throws RecipeNotFoundException {
        Recipe recipe = recipeDao.findRecipe(id);
        if (recipe == null)
            throw new RecipeNotFoundException();
        return recipe;
    }

    @Override
    public List<Recipe> findAllRecipes() {
        return recipeDao.findallRecipes();
    }

}

