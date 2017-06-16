package core.ejb.dao;

import core.api.dao.EmployeeDAO;
import core.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Session Bean implementation class DefaultEmployeeDAO
 */
@Stateless
@PersistenceContext
public class DefaultEmployeeDAO implements EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Employee findEmployee(Long id) {
		return entityManager.find(Employee.class, id);
	}

	@SuppressWarnings("unchecked")

	public List<Employee> findAllEmployees() {
		return entityManager.createNamedQuery("Employee.findAllOrdered").getResultList();
	}

	public Employee mergeEmployee(Employee employee) {
		return entityManager.merge(employee);
	}

	public void persistEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	public void removeEmployee(Employee employee) {
		entityManager.remove(employee);
	}

}
