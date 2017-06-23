package application.core.beans.recipe;


import application.core.api.exception.RecipeNotFoundException;
import application.core.api.manager.MedicineManager;
import application.core.api.manager.MedicineOrderManager;
import application.core.api.manager.RecipeManager;
import application.core.beans.utility.MessageBean;
import application.core.model.Employee;
import application.core.model.Medicine;
import application.core.model.MedicineOrder;
import application.core.model.Recipe;
import application.core.session.SessionUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "recipeBean")
@ViewScoped
public class RecipeBean implements Serializable {

    @EJB(beanInterface = RecipeManager.class)
    private RecipeManager recipeManager;

    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    @EJB(beanInterface = MedicineOrderManager.class)
    private MedicineOrderManager medicineOrderManager;

    //inject recipe information for adding and editing purposes
    @ManagedProperty(value = "#{recipeInformationBean}")
    RecipeInformationBean recipeInformationBean;

    @ManagedProperty(value="#{messageBean}")
    MessageBean messageBean;


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
        System.out.println("removing recipe: " + recipe.toString());
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
        //prepare recipe from form data
        Recipe recipe = new Recipe();
        recipe.setId(recipeInformationBean.getId());
        System.out.println("Inserting recipe with id = " + recipe.getId());
        recipe.setEmployee(loggedEmployee);
        recipe.setClient(recipeInformationBean.getClient());
        recipe.setMedicines(recipeInformationBean.getMedicines());
        System.out.println("addRecipe: size: "+recipe.getMedicines());

        if (recipe.getMedicines() != null) {
            for (MedicineOrder order : recipe.getMedicines()
                    ) {
                order.setRecipe(recipe);
            }
        }
        System.out.println("Employee id = " + recipe.getEmployee().getId());
        recipe = recipeManager.mergeRecipe(recipe);
        recipeInformationBean.setId(recipe.getId());
        messageBean.addMessage("Recipe updated successfully, to work with other recipe change or clear 'id' field");
    }

    public List<Recipe> getFilteredRecipes() {
        return filteredRecipes;
    }

    public void setFilteredRecipes(List<Recipe> filteredRecipes) {
        this.filteredRecipes = filteredRecipes;
    }

    public void removeMedicineOrder(MedicineOrder medicineOrder) {
        if (recipeInformationBean.getId() != null) {
            Long newQuantity = medicineOrder.getMedicine().getQuantity() + medicineOrder.getQuantity();
            medicineOrder.getMedicine().setQuantity(newQuantity);
            System.out.println("removeMedicineOrder: size: before "+recipeInformationBean.getMedicines().size());
            recipeInformationBean.getMedicines().remove(medicineOrder);
            System.out.println("removeMedicineOrder: size: "+recipeInformationBean.getMedicines().size());
            medicineOrderManager.removeMedicineOrder(medicineOrder);
            addRecipe();
        }
    }

    public void addMedicineOrder() {
        //prevent adding to null order.
        if (recipeInformationBean.getId() == null) {
            messageBean.addMessage("First create medicine order by clicking save.");
            return;
        }

        //prevent null on MedicineOrders
        if (recipeInformationBean.getMedicines() == null) {
            recipeInformationBean.setMedicines(new ArrayList<MedicineOrder>());
        }

        //prevent duplicated medicines by adding quantity
        //prevent adding to negative stock
        for (MedicineOrder medicineOrder : recipeInformationBean.getMedicines()
                ) {
            if (medicineOrder.getMedicine().equals(recipeInformationBean.getSelectedMedicine())) {
                Medicine selectedMedicine = medicineOrder.getMedicine();
                //check if there is enough medicine
                if (selectedMedicine.getQuantity() < recipeInformationBean.getSelectedQuantity()) {
                    messageBean.addMessage("Not enough medicine in stock: " + medicineOrder.getMedicine().getName());
                    return;
                }
                //recalculate quantity in database
                selectedMedicine.setQuantity(
                        selectedMedicine.getQuantity() -  recipeInformationBean.getSelectedQuantity()
                );
                //update medicine order.
                Long newQuantity = medicineOrder.getQuantity() + recipeInformationBean.getSelectedQuantity();
                medicineOrder.setQuantity(newQuantity);
                addRecipe();
                return;
            }
        }

        Medicine selectedMedicine = recipeInformationBean.getSelectedMedicine();
        if (selectedMedicine.getQuantity() < recipeInformationBean.getSelectedQuantity()) {
            messageBean.addMessage("Not enough medicine in stock: " + selectedMedicine.getName());
            return;
        }

        //prepare medicine order
        MedicineOrder medicineOrder = new MedicineOrder();
        medicineOrder.setMedicine(recipeInformationBean.getSelectedMedicine());
        medicineOrder.setQuantity(recipeInformationBean.getSelectedQuantity());
        recipeInformationBean.getMedicines().add(medicineOrder);

        //recalculate quantity in database
        selectedMedicine.setQuantity(
                selectedMedicine.getQuantity() -  recipeInformationBean.getSelectedQuantity()
        );

        addRecipe();

    }

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }

    public MedicineOrderManager getMedicineOrderManager() {
        return medicineOrderManager;
    }

    public void setMedicineOrderManager(MedicineOrderManager medicineOrderManager) {
        this.medicineOrderManager = medicineOrderManager;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    public void setLoggedEmployee(Employee loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }
}

