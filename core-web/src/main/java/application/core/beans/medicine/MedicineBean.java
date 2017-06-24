package application.core.beans.medicine;


import application.core.api.exception.MedicineNotFoundException;
import application.core.api.manager.MedicineManager;
import application.core.beans.utility.MessageBean;
import application.core.model.Medicine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "medicineBean")
@RequestScoped
public class MedicineBean implements Serializable {
    public List<Medicine> filteredMedicines;

    //inject medicine information for adding and editing purposes
    @ManagedProperty(value = "#{medicineInformationBean}")
    MedicineInformationBean medicineInformationBean;

    @ManagedProperty(value = "#{messageBean}")
    MessageBean messageBean;
    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    public MedicineBean() {


    }

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

    public List<Medicine> getMedicines() {
        return medicineManager.findAllMedicines();
    }

    public void removeMedicine(Medicine medicine) {
        System.out.println("removing medicine: " + medicine.toString());
        medicineManager.removeMedicine(medicine);

    }


    public void updateInformationBean() {
        Medicine medicine = null;

        try {
            System.out.println("updateInformationBean: searching medicine with name: " +
                    "" + medicineInformationBean.getName());
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
        medicineInformationBean.setMinQuantity(medicine.getMinQuantity());
        medicineInformationBean.setMaxQuantity(medicine.getMaxQuantity());
        medicineInformationBean.setType(medicine.getType());
        medicineInformationBean.setReplacementGroup(medicine.getReplacementGroup());
        medicineInformationBean.setName(medicine.getName());
        medicineInformationBean.setOrders(medicine.getOrders());
    }

    public void addMedicine() {
        Medicine medicine = new Medicine();
        medicine.setId(medicineInformationBean.getId());
        System.out.println("Inserting medicine with id = " + medicine.getId());
        medicine.setReplacementGroup(medicineInformationBean.getReplacementGroup());
        medicine.setType(medicineInformationBean.getType());
        medicine.setQuantity(medicineInformationBean.getQuantity());
        medicine.setMinQuantity(medicineInformationBean.getMinQuantity());
        medicine.setMaxQuantity(medicineInformationBean.getMaxQuantity());
        medicine.setDescription(medicineInformationBean.getDescription());
        medicine.setPrice(medicineInformationBean.getPrice());
        medicine.setName(medicineInformationBean.getName());
        medicine.setOrders(medicineInformationBean.getOrders());

        System.out.println("id = " + medicine.getId());
        System.out.println("name = " + medicine.getName());
        System.out.println("type = " + medicine.getType());

        medicine = medicineManager.mergeMedicine(medicine);
        medicineInformationBean.setId(medicine.getId());
        messageBean.addMessage("Medicine updated successfully, to work with other medicine change 'name' field");

    }

    public List<Medicine> getFilteredMedicines() {
        return filteredMedicines;
    }

    public void setFilteredMedicines(List<Medicine> filteredMedicines) {
        this.filteredMedicines = filteredMedicines;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }
}

