package application.core.beans;


import application.core.api.manager.EmployeeManager;
import application.core.beans.utility.NavigationBean;
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

    private final boolean REQUIRE_LOGIN = false;

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    //inject navigation bean
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;

    private Employee employee = SessionUtils.getEmployee();

    public EmployeeBean() {
    }

    public void initialize() {

        System.out.println("EmployeeBean: initialize");
        if (REQUIRE_LOGIN && !isLoggedIn()) {
            navigationBean.redirectFromTop("/login.xhtml");
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

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }


}
