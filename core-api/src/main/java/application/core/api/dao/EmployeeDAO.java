package application.core.api.dao;


import application.core.model.Employee;

import javax.ejb.Local;
import java.util.List;


@Local
public interface EmployeeDAO {

    void removeEmployee(Employee employee);

    void persistEmployee(Employee employee);

    Employee mergeEmployee(Employee employee);

    Employee findEmployee(Long id);

    List<Employee> findAllEmployees();

    List<Employee> findEmployeesByCredentials(String username, String password);

    List<Employee> findAllEmployeesByUsername(String username);
}
