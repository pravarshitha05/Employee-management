import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryInMemoryImpl implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    
    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    
    public Employee update(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                return employee;
            }
        }
        return null;
    }

    
    public boolean delete(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }

    
    public Employee findById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    
    public List<Employee> findAll() {
        return new ArrayList<>(employees); 
    }

    public static void main(String[] args) {
        EmployeeRepositoryInMemoryImpl repo = new EmployeeRepositoryInMemoryImpl();

        
        repo.add(new Employee(1, "pravarshitha", "pravarshitha@company.com", 75100, 10));
        repo.add(new Employee(2, "sindhu", "sindhu@company.com", 68000, 10));
        repo.add(new Employee(3, "pooja", "pooja@company.com", 82000, 20));

        
        Manager manager = new Manager(4, "Abhi", "Abhi@company.com", 95000, 10);
        Employee Pravarshitha = repo.findById(1);
        Employee sindhu = repo.findById(2);
        manager.addReport(Pravarshitha);
        manager.addReport(sindhu);
        repo.add(manager);

        System.out.println("findAll()");
        for (Employee e : repo.findAll()) {
            System.out.println(e);
        }

        System.out.println("\n findById(2)");
        System.out.println(repo.findById(2));

        System.out.println("\n update employee 2's email and salary");
        Employee updatedsindhu = new Employee(2, "sindhu", "sindhureddy@company.com", 72000, 10);
        repo.update(updatedsindhu);
        System.out.println(repo.findById(2));

        System.out.println("\n delete employee 3");
        boolean deleted = repo.delete(3);
        System.out.println("Deleted: " + deleted);

        System.out.println("\n findAll() ");
        for (Employee e : repo.findAll()) {
            System.out.println(e);
        }

        
    }
}
