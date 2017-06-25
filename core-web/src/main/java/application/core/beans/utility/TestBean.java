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
        client.setLastName("Reczyński");
        client.setPesel(96050236275L);
        client.setPhoneNumber("85739576");

        Client client2 = new Client();
        client2.setFirstName("Konrad");
        client2.setLastName("Ryłowicz");
        client2.setPesel(93100406576L);
        client2.setPhoneNumber("94375967");

        Client client3 = new Client();
        client3.setFirstName("Kamil");
        client3.setLastName("Kamiński");
        client3.setPesel(98033006278L);
        client3.setPhoneNumber("54907235");

        Employee employee = new Employee();
        employee.setFirstName("employee");
        employee.setLastName("Marek");
        employee.setUsername("Komar");
        employee.setPassword("employee");

        ReplacementGroup replacementGroup = new ReplacementGroup();
        replacementGroup.setName("replacements1");

        ReplacementGroup replacementGroup2 = new ReplacementGroup();
        replacementGroup2.setName("replacements2");

        Medicine medicine = new Medicine();
        medicine.setName("apap");
        medicine.setDescription("12 tabletek");
        medicine.setQuantity(50L);
        medicine.setMinQuantity(10L);
        medicine.setMaxQuantity(50L);
        medicine.setType("bought");
        medicine.setPrice(new BigDecimal(3.79));

        Medicine medicine2 = new Medicine();
        medicine2.setName("ibuprom");
        medicine2.setDescription("10 tabletek");
        medicine2.setQuantity(60L);
        medicine2.setMinQuantity(30L);
        medicine2.setMaxQuantity(90L);
        medicine2.setType("bought");
        medicine2.setPrice(new BigDecimal(3.89));

        Medicine medicine3 = new Medicine();
        medicine3.setName("rutinoscorbin");
        medicine3.setDescription("90 tabletek");
        medicine3.setQuantity(150L);
        medicine3.setMinQuantity(50L);
        medicine3.setMaxQuantity(100L);
        medicine3.setType("produced");
        medicine3.setPrice(new BigDecimal(5.98));

        replacementGroup  = replacementGroupManager.mergeReplacementGroup(replacementGroup);
        replacementGroup2 = replacementGroupManager.mergeReplacementGroup(replacementGroup2);

        medicine.setReplacementGroup(replacementGroup);
        medicine2.setReplacementGroup(replacementGroup);
        medicine3.setReplacementGroup(replacementGroup2);

        medicineManager.mergeMedicine(medicine);
        medicineManager.mergeMedicine(medicine2);
        medicineManager.mergeMedicine(medicine3);

        clientManager.mergeClient(client);
        clientManager.mergeClient(client2);
        clientManager.mergeClient(client3);
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
