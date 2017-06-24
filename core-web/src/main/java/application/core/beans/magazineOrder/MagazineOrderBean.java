package application.core.beans.magazineOrder;


import application.core.api.manager.MedicineManager;
import application.core.beans.utility.MessageBean;
import application.core.model.Medicine;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "magazineOrderBean")
@RequestScoped
public class MagazineOrderBean implements Serializable {

    @ManagedProperty(value = "#{messageBean}")
    MessageBean messageBean;
    List<MagazineOrder> magazineOrders = new ArrayList<MagazineOrder>();
    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    @PostConstruct
    public void init() {
        List<Medicine> medicines = medicineManager.findAllMedicines();
        for (Medicine medicine : medicines) {
            if (!medicine.getType().equals("bought")) {
                //ignore produced medicines
                continue;
            }
            if (medicine.getQuantity() < medicine.getMinQuantity()) {
                MagazineOrder magazineOrder = new MagazineOrder();
                Long orderQuantity = medicine.getMaxQuantity() - medicine.getQuantity();

                magazineOrder.setMedicineName(medicine.getName());
                magazineOrder.setQuantity(orderQuantity);
                magazineOrders.add(magazineOrder);
            }
        }
    }

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public List<MagazineOrder> getMagazineOrders() {
        return magazineOrders;
    }

    public void setMagazineOrders(List<MagazineOrder> magazineOrders) {
        this.magazineOrders = magazineOrders;
    }
}
