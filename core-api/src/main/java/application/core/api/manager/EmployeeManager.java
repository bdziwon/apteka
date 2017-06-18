package application.core.api.manager;

import application.core.api.exception.EmployeeNotFoundException;
import application.core.model.Employee;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface EmployeeManager  {

	List<Employee> findAllEmployees();

	Employee findEmployee(Long id) throws EmployeeNotFoundException;

	Employee mergeEmployee(Employee employee);

	void persistEmployee(Employee employee);

	void removeEmployee(Employee employee);

	List<Employee> findEmployeesByCredentials(String username, String password);

    List<Employee> findEmployeesByUsername(String username);
}
