package core.ejb.manager;

import core.api.dao.EmployeeDAO;
import core.api.exception.EmployeeNotFoundException;
import core.api.manager.EmployeeManager;
import core.model.Employee;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class DefaultEmployeeManager
 */
@Stateless
public class DefaultEmployeeManager implements EmployeeManager {

	@EJB
	private EmployeeDAO employeeDAO;

	@Override
	public void removeEmployee(Employee employee) {
		employeeDAO.removeEmployee(employee);
	}

	@Override
	public void persistEmployee(Employee employee) {
		employeeDAO.persistEmployee(employee);
	}

	@Override
	public Employee mergeEmployee(Employee employee) {
		return employeeDAO.mergeEmployee(employee);
	}

	@Override
	public Employee findEmployee(Long id) throws EmployeeNotFoundException {
		Employee employee = employeeDAO.findEmployee(id);
		if (employee == null)
			throw new EmployeeNotFoundException();
		return employee;
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeDAO.findAllEmployees();
	}

}
