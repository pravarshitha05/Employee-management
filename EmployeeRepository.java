import java.util.List;

public interface EmployeeRepository {
    Employee add(Employee employee);
    Employee update(Employee employee);
    boolean delete(int id);
    Employee findById(int id);
    List<Employee> findAll();
}
