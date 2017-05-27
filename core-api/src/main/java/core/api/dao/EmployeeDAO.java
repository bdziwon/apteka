package core.api.dao;

import core.model.Employee;

import java.util.List;

import javax.ejb.Local;


@Local
public interface EmployeeDAO {

	void removeEmployee(Employee employee);

	void persistEmployee(Employee employee);

	Employee mergeEmployee(Employee employee);

	Employee findEmployee(Long id);

	List<Employee> findAllEmployees();

}
