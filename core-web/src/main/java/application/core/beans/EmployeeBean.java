package application.core.beans;


import application.core.api.manager.EmployeeManager;
import application.core.model.Employee;
import application.core.session.SessionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;


@ManagedBean(name = "employeeBean")
@RequestScoped
public class EmployeeBean implements Serializable {

    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;

    private Employee employee = SessionUtils.getEmployee();

    public EmployeeBean() {
    }

    public void initialize() {

        System.out.println("EmployeeBean: initialize");
        if (!isLoggedIn()) {
            try {
                redirectToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**

     * checks if session is logged
     *
     * @return true if logged, false if not
     */
    public boolean isLoggedIn() {
        return employee != null;
    }

    public void redirectToLogin() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    public void redirectToMedicines() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("medicines");
    }


    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }


}
