package mbeans;

import core.api.dao.EmployeeDAO;
import core.api.manager.EmployeeManager;
import core.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@ManagedBean
@RequestScoped
public class EmployeeBean {

    private Employee employee;

    private EmployeeManager employeeManager;

    public EmployeeBean() {
        Context ctx = null;
        try {
            ctx = new InitialContext();
          employeeManager = (EmployeeManager) ctx.lookup("java:app/core-ejb/DefaultEmployeeManager");

        } catch (NamingException e) {
            e.printStackTrace();
        }
        employee = new Employee();
        employee.setFirstName("Bartek");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

//    public String actionMethod() {
//        employeeDAO.persistEmployee(employee);
//        return "response";
//    }
}
