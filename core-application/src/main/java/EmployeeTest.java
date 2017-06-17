import core.api.manager.EmployeeManager;
import core.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Local;

/**
 * Created by Bartek on 17.06.2017.
 */

@Local
public class EmployeeTest {
    @EJB
    EmployeeManager manager;
    public void test() {
        manager.findAllEmployees();
        System.out.println("test called");
    }
}
