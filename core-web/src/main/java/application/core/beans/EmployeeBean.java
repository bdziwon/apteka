package application.core.beans;


import application.core.api.manager.EmployeeManager;
import application.core.model.Employee;
import application.core.model.Recipe;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;


@Stateless
@ManagedBean(name = "employeeBean")
@RequestScoped
public class EmployeeBean {

    @EJB(beanInterface = EmployeeManager.class)
    private EmployeeManager employeeManager;

    public EmployeeBean() {
    }

    public String hello() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("name");
        employee.setLastName("surname");
        employeeManager.persistEmployee(employee);

        return String.valueOf(employeeManager.findAllEmployees().size());

    }

}


