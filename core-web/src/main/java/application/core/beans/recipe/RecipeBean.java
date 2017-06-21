package application.core.beans.recipe;


import application.core.api.exception.RecipeNotFoundException;
import application.core.api.manager.RecipeManager;
import application.core.model.Employee;
import application.core.model.Recipe;
import application.core.session.SessionUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Hubert on 2017-06-18.
 */
@ManagedBean(name = "recipeBean")
@RequestScoped
public class RecipeBean implements Serializable {
    @EJB(beanInterface = RecipeManager.class)
    private RecipeManager recipeManager;

    //inject recipe information for adding and editing purposes
    @ManagedProperty(value="#{recipeInformationBean}")
    RecipeInformationBean recipeInformationBean;

    public List<Recipe> filteredRecipes;

    Employee loggedEmployee = SessionUtils.getEmployee();


    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public void setRecipeManager(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    public RecipeInformationBean getRecipeInformationBean() {
        return recipeInformationBean;
    }

    public void setRecipeInformationBean(RecipeInformationBean recipeInformationBean) {
        this.recipeInformationBean = recipeInformationBean;
    }

    public RecipeBean() {


    }

    public List<Recipe> getRecipes() {
        //only logged employee
        Employee employee = SessionUtils.getEmployee();
        return recipeManager.findRecipesByEmployee(employee);
    }

    public void removeRecipe(Recipe recipe) {
        System.out.println("removing recipe: "+recipe.toString());
        recipeManager.removeRecipe(recipe);

    }


    public void updateInformationBean() {
        Recipe recipe = null;

        try {
            recipe = recipeManager.findRecipe(recipeInformationBean.getId());
        } catch (RecipeNotFoundException e) {
            recipeInformationBean.setId(null);
            System.out.println("updateInformationBean:RecipeNotFoundException");
            return;
        }


        //update fields with selected recipe.
        System.out.println("updateInformationBean: updating fields");
        System.out.println("Id from database = " + recipe.getId());
        recipeInformationBean.setId(recipe.getId());
        recipeInformationBean.setClient(recipe.getClient());
        recipeInformationBean.setEmployee(loggedEmployee);
        recipeInformationBean.setMedicines(recipe.getMedicines());
    }

    public void addRecipe() {
            Recipe recipe = new Recipe();
            recipe.setId(recipeInformationBean.getId());
            System.out.println("Inserting recipe with id = " +recipe.getId());
            recipe.setEmployee(loggedEmployee);
            recipe.setClient(recipeInformationBean.getClient());
            recipe.setMedicines(recipeInformationBean.getMedicines());

        System.out.println("Employee id = " + recipe.getEmployee().getId());
            recipe = recipeManager.mergeRecipe(recipe);
            recipeInformationBean.setId(recipe.getId());
            addMessage("Recipe updated successfully, to work with other recipe change or clear 'id' field");

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Recipe> getFilteredRecipes() {
        return filteredRecipes;
    }

    public void setFilteredRecipes(List<Recipe> filteredRecipes) {
        this.filteredRecipes = filteredRecipes;
    }

}

