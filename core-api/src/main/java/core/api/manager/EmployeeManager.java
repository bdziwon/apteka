package core.api.manager;

import java.util.List;


import core.api.exception.EmployeeNotFoundException;
import core.model.Employee;

import javax.ejb.Remote;

@Remote
public interface EmployeeManager  {

	List<Employee> findAllEmployees();

	Employee findEmployee(Long id) throws EmployeeNotFoundException;

	Employee mergeEmployee(Employee employee);

	void persistEmployee(Employee employee);

	void removeEmployee(Employee employee);

}
