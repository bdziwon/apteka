package application.core.beans.medicine;

import application.core.api.exception.MedicineNotFoundException;
import application.core.api.manager.MedicineManager;
import application.core.model.Medicine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Bartek on 20.06.2017.
 */

@FacesConverter(value = "medicineConverter")
@ManagedBean(name = "medicineConverterBean")
public class MedicineConverterBean implements Converter {

    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("getAsObject String: " + s);
        try {
            Medicine medicine
                    = medicineManager.findMedicine(new Long(s));
            System.out.println("getAsObject result: " + medicine.getId());

            return medicine;
        } catch (MedicineNotFoundException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        String result = String.valueOf(((Medicine) o).getId());
        System.out.println("getAsString:" + result);
        return result;
    }

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }

}