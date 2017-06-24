package application.core.beans.utility;

import application.core.api.manager.*;
import application.core.model.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.math.BigDecimal;

@RequestScoped
@ManagedBean(name = "testBean")
public class TestBean {

    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;

    @EJB(beanInterface = ClientManager.class)
    private ClientManager clientManager;

    @EJB(beanInterface = ReplacementGroupManager.class)
    private ReplacementGroupManager replacementGroupManager;


    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    public void initTestDb() {
        Client client = new Client();
        client.setFirstName("Adam");
        client.setLastName("Gogol");
        client.setPesel(96119293845L);
        client.setPhoneNumber("883742423");

        Employee employee = new Employee();
        employee.setFirstName("lorkano");
        employee.setLastName("lorkano");
        employee.setUsername("lorkano");
        employee.setPassword("lorkano");

        ReplacementGroup replacementGroup = new ReplacementGroup();
        replacementGroup.setName("rg");

        Medicine medicine = new Medicine();
        medicine.setName("apap");
        medicine.setDescription("for headache");
        medicine.setQuantity(20L);
        medicine.setType("bought");
        medicine.setPrice(new BigDecimal(50));

        Medicine medicine2 = new Medicine();
        medicine2.setName("ibuprom");
        medicine2.setDescription("for headache");
        medicine2.setQuantity(30L);
        medicine2.setType("bought");
        medicine2.setPrice(new BigDecimal(30));

        replacementGroup = replacementGroupManager.mergeReplacementGroup(replacementGroup);

        medicine.setReplacementGroup(replacementGroup);


        medicineManager.mergeMedicine(medicine);
        medicineManager.mergeMedicine(medicine2);
        clientManager.mergeClient(client);
        employeeManager.mergeEmployee(employee);
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public ReplacementGroupManager getReplacementGroupManager() {
        return replacementGroupManager;
    }

    public void setReplacementGroupManager(ReplacementGroupManager replacementGroupManager) {
        this.replacementGroupManager = replacementGroupManager;
    }

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }
}
