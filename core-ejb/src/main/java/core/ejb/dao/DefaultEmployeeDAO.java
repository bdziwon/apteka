package core.ejb.dao;

import core.api.dao.EmployeeDAO;
import core.model.Employee;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 * Session Bean implementation class DefaultEmployeeDAO
 */
@Stateless
public class DefaultEmployeeDAO implements EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Employee findEmployee(Long id) {
		return entityManager.find(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAllEmployees() {
		return entityManager.createNamedQuery("Employee.findAllOrdered").getResultList();
	}

	@Override
	public Employee mergeEmployee(Employee employee) {
		return entityManager.merge(employee);
	}

	@Override
	public void persistEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Override
	public void removeEmployee(Employee employee) {
		entityManager.remove(employee);
	}

}
