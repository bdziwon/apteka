package core.ejb.manager;

import core.api.exception.EmployeeNotFoundException;
import core.api.dao.EmployeeDAO;
import core.api.manager.EmployeeManager;
import core.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Session Bean implementation class DefaultEmployeeManager
 */
@Stateless(mappedName = "DefaultEmployeeManager")
public class DefaultEmployeeManager implements EmployeeManager {

	@EJB
	private EmployeeDAO employeeDAO;

	public void removeEmployee(Employee employee) {
		employeeDAO.removeEmployee(employee);
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
