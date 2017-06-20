package application.core.beans.replacement;

import application.core.api.exception.ReplacementGroupNotFoundException;
import application.core.api.manager.ReplacementGroupManager;
import application.core.beans.medicine.MedicineInformationBean;
import application.core.model.ReplacementGroup;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigInteger;
import java.util.Locale;

/**
 * Created by Bartek on 20.06.2017.
 */

@FacesConverter(value = "replacementConverter")
@ManagedBean(name = "replacementConverterBean")
public class ReplacementConverterBean implements Converter {

    @EJB(beanInterface = ReplacementGroupManager.class)
    private ReplacementGroupManager replacementGroupManager;

    @ManagedProperty(value = "#{medicineInformationBean}")
    MedicineInformationBean medicineInformationBean;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("getAsObject String: " + s);
        try {
            ReplacementGroup replacementGroup
                    = replacementGroupManager.findReplacementGroup(new Long(s));
            System.out.println("getAsObject result: " + replacementGroup.getId());
            medicineInformationBean.setReplacementGroup(replacementGroup);
            return replacementGroup;
        } catch (ReplacementGroupNotFoundException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        String result = String.valueOf(((ReplacementGroup) o).getId());
        System.out.println("getAsString:" + result);
        return result;
    }

    public ReplacementGroupManager getReplacementGroupManager() {
        return replacementGroupManager;
    }

    public void setReplacementGroupManager(ReplacementGroupManager replacementGroupManager) {
        this.replacementGroupManager = replacementGroupManager;
    }

    public MedicineInformationBean getMedicineInformationBean() {
        return medicineInformationBean;
    }

    public void setMedicineInformationBean(MedicineInformationBean medicineInformationBean) {
        this.medicineInformationBean = medicineInformationBean;
    }
}