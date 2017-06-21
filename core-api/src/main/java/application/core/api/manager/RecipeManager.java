package application.core.api.manager;

import application.core.api.exception.RecipeNotFoundException;
import application.core.model.Employee;
import application.core.model.Recipe;

import java.util.List;


import javax.ejb.Remote;

/**
 * Created by Hubert on 2017-05-28.
 */
@Remote
public interface RecipeManager {
    List<Recipe> findAllRecipes();

    Recipe findRecipe(Long id) throws RecipeNotFoundException;

    Recipe mergeRecipe(Recipe recipe);

    void persistRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);


    List<Recipe> findRecipesByEmployee(Employee employee);
}
