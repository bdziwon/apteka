package core.api.dao;

import core.model.Employee;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import java.util.List;


@Local
public interface EmployeeDAO {

	void removeEmployee(Employee employee);

	void persistEmployee(Employee employee);

	Employee mergeEmployee(Employee employee);

	Employee findEmployee(Long id);

	List<Employee> findAllEmployees();

}
