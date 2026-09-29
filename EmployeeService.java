import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repository =
            new EmployeeRepositoryInMemoryImpl();

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(int id) {
     Employee employee = repository.findById(id);

     if (employee == null) {
         throw new EmployeeNotFoundException(
                "Employee with ID " + id + " not found"
         );
    }

    return employee;
}

    public Employee addEmployee(Employee employee) {
        return repository.add(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.update(employee);
    }

    public boolean deleteEmployee(int id) {
        return repository.delete(id);
    }
}
