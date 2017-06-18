package application.core.beans;


import application.core.annotations.LoggedIn;
import application.core.api.manager.EmployeeManager;
import application.core.model.Employee;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Session class, represents current logged user
 */
@ManagedBean(name = "loginSession")
@SessionScoped
public class LoginSession implements Serializable {

    //inject credentials
    @Inject
    CredentialsBean credentalsBean;

    //employeeManager for operations
    @EJB(beanInterface = EmployeeManager.class)
    EmployeeManager employeeManager;

    //logged employee, should be not null when logged
    private Employee employee;

    public CredentialsBean getCredentalsBean() {
        return credentalsBean;
    }

    public void setCredentalsBean(CredentialsBean credentalsBean) {
        this.credentalsBean = credentalsBean;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public LoginSession() {

    }

    public void login() {
        String  username    =   credentalsBean.getUsername();
        String  password    =   credentalsBean.getPassword();
        employeeManager.findEmployeesByCredentials(username, password);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Checks if Employee is logged
     * @return true when Session is logged in,
     * false otherwise
     */
    public boolean isLoggedIn() {
        return employee != null;
    }

    /**
     * Allows us to get inject logged employee by annotation
     * @return logged employee or null if not logged
     */
    @Produces
    @LoggedIn
    public Employee getLoggedEmployee() {
        return employee;
    }
}
