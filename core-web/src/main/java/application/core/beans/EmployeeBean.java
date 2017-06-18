package application.core.beans;


import application.core.annotations.LoggedIn;
import application.core.api.manager.EmployeeManager;
import application.core.model.Employee;
import application.core.model.Recipe;

import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


@ManagedBean(name = "employeeBean")
@RequestScoped
public class EmployeeBean implements Serializable {

    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;


    @ManagedProperty(value="#{loginSession.employee}")
    private Employee loggedEmployee;

    public EmployeeBean() {
    }

    /**
     * checks if session is logged
     * @return true if logged, false if not
     */
    public boolean isLoggedIn() {
//        return loginSession.isLoggedIn();
        return loggedEmployee != null;
    }

    public void redirectToLogin() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("login.xhtml");
    }

    public void initialize() {
        if (!isLoggedIn()) {
            try {
                redirectToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    public void setLoggedEmployee(Employee loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

//    public LoginSession getLoginSession() {
//        return loginSession;
//    }
//
//    public void setLoginSession(LoginSession loginSession) {
//        this.loginSession = loginSession;
//    }
}


