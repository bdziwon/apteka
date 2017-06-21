package application.core.beans.utility;


import application.core.annotations.LoggedIn;
import application.core.api.manager.EmployeeManager;
import application.core.beans.autentication.CredentialsBean;
import application.core.beans.autentication.RegisterInformationBean;
import application.core.model.Employee;
import application.core.session.SessionUtils;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;

/**
 * Session class, represents current logged user
 */

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private final int expirationTimeInSeconds = 120;

    //inject credentials
    @ManagedProperty(value="#{credentialsBean}")
    private CredentialsBean credentialsBean;

    //inject navigation bean
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;

    //inject registering informations
    @ManagedProperty(value="#{registerInformationBean}")
    private RegisterInformationBean registerInformationBean;

    //inject employeeManager for operations
    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;

    //logged employee, should be not null when logged
    private Employee employee = SessionUtils.getEmployee();

    public SessionBean() {

    }

    /**
     * performs login operation with given credentials
     */
    public void login(ActionEvent actionEvent) {

        String          username            =   credentialsBean.getUsername();
        String          password            =   credentialsBean.getPassword();
        List<Employee>  matchedEmployees;

        System.out.println("logging in with:");
        System.out.println("username = "+username);
        System.out.println("password = "+password);
        matchedEmployees = employeeManager.findEmployeesByCredentials(username, password);

        if (matchedEmployees.isEmpty()) {
            addMessage("User does not exist");
            //TODO: user not found, login failed
        } else {
            //validation successful
            employee = matchedEmployees.get(0);
            HttpSession session = SessionUtils.getSession();

            //checks if user is already logged (pervent 2 cients)
            if (SessionUtils.getEmployee() != null) {
                addMessage("This session is already logged, log out to sign into new user");
                return;
            }
            session.setAttribute("employee",employee);
            session.setMaxInactiveInterval(expirationTimeInSeconds);

            //redirect to index
            try {
                redirectToIndex();
                addMessage("Login sucessful");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout(ActionEvent actionEvent) {
        if (!isLoggedIn()) {
            addMessage("Already logged out");
        } else {
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
        }
        try {
            redirectToIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void register(ActionEvent actionEvent) {
        String          username            =   credentialsBean.getUsername();
        String          password            =   credentialsBean.getPassword();
        String          firstName           =   registerInformationBean.getFirstName();
        String          lastName            =   registerInformationBean.getLastName();
        List<Employee>  matchedEmployees;

        System.out.println("Registering firstName = "+firstName);
        System.out.println("Registering lastName = "+lastName);

        matchedEmployees = employeeManager.findEmployeesByUsername(username);

        //checking if employee exist
        if (!matchedEmployees.isEmpty()) {
            addMessage("This username is occupied.");
        } else {

            //creating new employee
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setUsername(username);
            employee.setPassword(password);

            employee = employeeManager.mergeEmployee(employee);

            this.employee = employee;

            HttpSession session = SessionUtils.getSession();

            //checks if user is already logged (locally)
            if (SessionUtils.getEmployee() != null) {
                addMessage("This session is logged, log out to sign into new user");
                return;
            }
            session.setAttribute("employee",employee);
            session.setMaxInactiveInterval(expirationTimeInSeconds);

            //redirect to index
            try {
                redirectToIndex();
                addMessage("Register sucessful");
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


    public void redirectToIndex() throws IOException {
        //show message when cant go back - logged out
        if (!isLoggedIn()) {
            addMessage("You have to be logged in for this operation.");
        } else {
            navigationBean.redirectTo("index.xhtml");
        }

    }

    public void redirectToLogin() throws IOException {
        navigationBean.redirectTo("login.xhtml");
        addMessage("You have to be logged in.");
    }


    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public CredentialsBean getCredentialsBean() {
        return credentialsBean;
    }

    public void setCredentialsBean(CredentialsBean credentialsBean) {
        this.credentialsBean = credentialsBean;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public int getExpirationTimeInSeconds() {
        return expirationTimeInSeconds;
    }

    public RegisterInformationBean getRegisterInformationBean() {
        return registerInformationBean;
    }

    public void setRegisterInformationBean(RegisterInformationBean registerInformationBean) {
        this.registerInformationBean = registerInformationBean;
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
