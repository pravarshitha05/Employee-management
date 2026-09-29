import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryInMemoryImpl implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private final Map<Integer, List<Employee>> employeesByDepartment= new HashMap<>();
    private final Map<Integer, List<Employee>> employeesByManager= new HashMap<>();
    @Override
    public Employee add(Employee employee) {

    for (Employee existingEmployee : employees) {
        if (existingEmployee.getId() == employee.getId()) {
            throw new DuplicateEmployeeException(
                    "Employee with ID " + employee.getId() + " already exists"
            );
        }
    }

    employees.add(employee);

    employeesByDepartment
            .computeIfAbsent(
                    employee.getDepartmentId(),
                    key -> new ArrayList<>()
            )
            .add(employee);

    if (employee.getManagerId() != null) {
        employeesByManager
                .computeIfAbsent(
                        employee.getManagerId(),
                        key -> new ArrayList<>()
                )
                .add(employee);
    }

    return employee;
}
    @Override
    public Employee update(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            Employee oldEmployee = employees.get(i);
            if (oldEmployee.getId() == employee.getId()) {
                removeFromDepartmentIndex(oldEmployee);
                removeFromManagerIndex(oldEmployee);
                employees.set(i, employee);
                addToDepartmentIndex(employee);
                addToManagerIndex(employee);
                return employee;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Integer id) {
        Employee employeeToDelete = findById(id);
        if (employeeToDelete == null) {
            return false;
        }
        employees.remove(employeeToDelete);
        removeFromDepartmentIndex(employeeToDelete);
        removeFromManagerIndex(employeeToDelete);
        return true;
    }
    @Override
    public Employee findById(Integer id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
    @Override
    public List<Employee> findByDepartment(int departmentId) {
        List<Employee> result =
                employeesByDepartment.get(departmentId);
        if (result == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(result);
    }
    @Override
    public List<Employee> findByManager(int managerId) {
        List<Employee> result =
                employeesByManager.get(managerId);

        if (result == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(result);
    }


    private void addToDepartmentIndex(Employee employee) {

        employeesByDepartment
                .computeIfAbsent(
                        employee.getDepartmentId(),
                        key -> new ArrayList<>()
                )
                .add(employee);
    }


    private void addToManagerIndex(Employee employee) {

        if (employee.getManagerId() != null) {

            employeesByManager
                    .computeIfAbsent(
                            employee.getManagerId(),
                            key -> new ArrayList<>()
                    )
                    .add(employee);
        }
    }


    private void removeFromDepartmentIndex(Employee employee) {

        List<Employee> departmentEmployees =
                employeesByDepartment.get(
                        employee.getDepartmentId()
                );

        if (departmentEmployees != null) {

            departmentEmployees.remove(employee);

            if (departmentEmployees.isEmpty()) {

                employeesByDepartment.remove(
                        employee.getDepartmentId()
                );
            }
        }
    }


    private void removeFromManagerIndex(Employee employee) {

        if (employee.getManagerId() == null) {
            return;
        }

        List<Employee> managerEmployees =
                employeesByManager.get(
                        employee.getManagerId()
                );

        if (managerEmployees != null) {

            managerEmployees.remove(employee);

            if (managerEmployees.isEmpty()) {

                employeesByManager.remove(
                        employee.getManagerId()
                );
            }
        }
    }


    public static void main(String[] args) {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();


        Employee employee1 = new Employee(
                1,
                "Pravarshitha",
                "pravarshitha@company.com",
                75100,
                10,
                4
        );

        Employee employee2 = new Employee(
                2,
                "Sindhu",
                "sindhu@company.com",
                68000,
                10,
                4
        );

        Employee employee3 = new Employee(
                3,
                "Pooja",
                "pooja@company.com",
                82000,
                20,
                5
        );


        repo.add(employee1);
        repo.add(employee2);
        repo.add(employee3);


        System.out.println("Employees in Department 10:");

        for (Employee employee : repo.findByDepartment(10)) {
            System.out.println(employee);
        }


        System.out.println("\nEmployees under Manager 4:");

        for (Employee employee : repo.findByManager(4)) {
            System.out.println(employee);
        }


        System.out.println("\nFind Employee by ID 2:");

        System.out.println(repo.findById(2));


        System.out.println("\nUpdating Employee 2...");

        Employee updatedEmployee2 = new Employee(
                2,
                "Sindhu",
                "sindhureddy@company.com",
                72000,
                20,
                5
        );

        repo.update(updatedEmployee2);

        System.out.println(repo.findById(2));


        System.out.println("\nEmployees in Department 20 after update:");

        for (Employee employee : repo.findByDepartment(20)) {
            System.out.println(employee);
        }


        System.out.println("\nEmployees under Manager 5 after update:");

        for (Employee employee : repo.findByManager(5)) {
            System.out.println(employee);
        }


        System.out.println("\nDeleting Employee 3...");

        boolean deleted = repo.delete(3);

        System.out.println("Deleted: " + deleted);


        System.out.println("\nAll employees after delete:");

        for (Employee employee : repo.findAll()) {
            System.out.println(employee);
        }
    }
}