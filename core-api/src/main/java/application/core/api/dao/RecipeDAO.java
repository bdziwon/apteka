package application.core.api.dao;


import application.core.model.Recipe;

import java.util.List;

import javax.ejb.Local;
@Local
/**
 * Created by Hubert on 2017-05-28.
 */
public interface RecipeDAO {
    void removeRecipe(Recipe recipe);

    void persistRecipe(Recipe recipe);


    Recipe mergeRecipe(Recipe recipe);

    Recipe findRecipe(Long id);

    List<Recipe> findallRecipes();
}
