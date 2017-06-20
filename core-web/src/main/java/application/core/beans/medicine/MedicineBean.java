package application.core.beans.medicine;


import application.core.api.exception.MedicineNotFoundException;
import application.core.api.manager.MedicineManager;
import application.core.model.Medicine;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Hubert on 2017-06-18.
 */
@ManagedBean(name = "medicineBean")
@RequestScoped
public class MedicineBean implements Serializable {
    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    //inject medicine information for adding and editing purposes
    @ManagedProperty(value="#{medicineInformationBean}")
    MedicineInformationBean medicineInformationBean;

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }

    public MedicineInformationBean getMedicineInformationBean() {
        return medicineInformationBean;
    }

    public void setMedicineInformationBean(MedicineInformationBean medicineInformationBean) {
        this.medicineInformationBean = medicineInformationBean;
    }

    public MedicineBean() {


    }

    public List<Medicine> getMedicines() {
        return medicineManager.findAllMedicines();
    }

    public void removeMedicine(Medicine medicine) {
        medicineManager.removeMedicine(medicine);
    }

    public void redirectToAddMedicine() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("add.xhtml");
    }

    public void updateInformationBean() {
        Medicine medicine = null;

        try {
            System.out.println("updateInformationBean: searching medicine with name: " +
                    ""+medicineInformationBean.getName());
            medicine = medicineManager.findMedicineByName(medicineInformationBean.getName());
        } catch (MedicineNotFoundException e) {
            //do not update any form fields, medicine with given name not found
            medicineInformationBean.setId(null);
            System.out.println("updateInformationBean:MedicineNotFoundException");
            return;
        }

        //update fields with selected medicine.
        System.out.println("updateInformationBean: updating fields");
        System.out.println("Id from database = " + medicine.getId());
        medicineInformationBean.setId(medicine.getId());
        medicineInformationBean.setDescription(medicine.getDescription());
        medicineInformationBean.setPrice(medicine.getPrice());
        medicineInformationBean.setQuantity(medicine.getQuantity());
        medicineInformationBean.setType(medicine.getType());
        medicineInformationBean.setReplacementGroup(medicine.getReplacementGroup());
        medicineInformationBean.setName(medicine.getName());
        medicineInformationBean.setOrders(medicine.getOrders());
    }

    public void addMedicine() {
            Medicine medicine = new Medicine();
            medicine.setId(medicineInformationBean.getId());
            System.out.println("Inserting medicine with id = " +medicine.getId());
            medicine.setReplacementGroup(medicineInformationBean.getReplacementGroup());
            medicine.setType(medicineInformationBean.getType());
            medicine.setQuantity(medicineInformationBean.getQuantity());
            medicine.setDescription(medicineInformationBean.getDescription());
            medicine.setPrice(medicineInformationBean.getPrice());
            medicine.setName(medicineInformationBean.getName());
            medicine.setOrders(medicineInformationBean.getOrders());

            System.out.println("id = " + medicine.getId());
            System.out.println("name = " + medicine.getName());
            System.out.println("type = " + medicine.getType());

            medicine = medicineManager.mergeMedicine(medicine);
            medicineInformationBean.setId(medicine.getId());
            addMessage("Medicine updated successfully, to work with other user change 'name' field");

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
