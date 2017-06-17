package application.core.beans;


import application.core.api.manager.EmployeeManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@Stateless
@ManagedBean(name = "employeeBean")
@RequestScoped
public class EmployeeBean {

    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;

    public EmployeeBean() {
    }

    public String hello() throws Exception {
        return String.valueOf(employeeManager.findAllEmployees().size());
    }

}


