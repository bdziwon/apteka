package application.core.ejb.manager;


import application.core.api.dao.EmployeeDAO;
import application.core.api.exception.EmployeeNotFoundException;
import application.core.api.manager.EmployeeManager;
import application.core.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DefaultEmployeeManager implements EmployeeManager {

    @EJB
    private EmployeeDAO employeeDAO;

    public void removeEmployee(Employee employee) {
        employeeDAO.removeEmployee(employee);
    }

    @Override
    public List<Employee> findEmployeesByCredentials(String username, String password) {
        return employeeDAO.findEmployeesByCredentials(username, password);
    }

    @Override
    public List<Employee> findEmployeesByUsername(String username) {
        return employeeDAO.findAllEmployeesByUsername(username);
    }

    public void persistEmployee(Employee employee) {
        employeeDAO.persistEmployee(employee);
    }

    public Employee mergeEmployee(Employee employee) {
        return employeeDAO.mergeEmployee(employee);
    }

    public Employee findEmployee(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeDAO.findEmployee(id);
        if (employee == null)
            throw new EmployeeNotFoundException();
        return employee;
    }

    public List<Employee> findAllEmployees() {
        return employeeDAO.findAllEmployees();
    }

}
