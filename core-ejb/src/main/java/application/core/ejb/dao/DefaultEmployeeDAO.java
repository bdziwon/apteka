package application.core.ejb.dao;


import application.core.api.dao.EmployeeDAO;
import application.core.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Session Bean implementation class DefaultEmployeeDAO
 */
@Stateless
@PersistenceContext(name = "EmployeeDAO")
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

	@Override
	public List<Employee> findEmployeesByCredentials(String username, String password) {
		return entityManager
				.createNamedQuery("Employee.findByCredentials")
				.setParameter("username", username)
				.setParameter("password", password)
				.getResultList();
	}

	public Employee mergeEmployee(Employee employee) {
		return entityManager.merge(employee);
	}

	public void persistEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	public void removeEmployee(Employee employee) {
		entityManager.remove(mergeEmployee(employee));
	}

}
