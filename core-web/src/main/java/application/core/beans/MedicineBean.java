package application.core.beans;


import application.core.annotations.LoggedIn;
import application.core.api.manager.MedicineManager;
import application.core.model.Employee;
import application.core.model.Medicine;
import application.core.model.Recipe;

import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hubert on 2017-06-18.
 */
@ManagedBean(name = "MedicineBean")
@RequestScoped
public class MedicineBean implements Serializable{
    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    public MedicineBean(){

    }
    public List<Medicine> getMedicines() {
        return medicineManager.findAllMedicines();
    }
    public void removeMedicine(Medicine medicine){
        medicineManager.removeMedicine(medicine);
    }


}
