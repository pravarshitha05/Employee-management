import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ReportService {

    // 1. Average salary per department
    public static Map<Integer, Double> averageSalaryByDepartment(
            List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartmentId,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    // 2. Top 3 earners overall
    public static List<Employee> topThreeEarners(
            List<Employee> employees) {

        return employees.stream()
                .sorted(
                        Comparator.comparingDouble(Employee::getSalary)
                                .reversed()
                )
                .limit(3)
                .collect(Collectors.toList());
    }

    // 3. Group employees by Department
    public static Map<Department, List<Employee>> employeesByDepartment(
            List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> new Department(
                                employee.getDepartmentId(),
                                "Department " + employee.getDepartmentId()
                        )
                ));
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(

                new Employee(
                        1,
                        "Pravarshitha",
                        "pravarshitha@company.com",
                        75100,
                        10,
                        4
                ),

                new Employee(
                        2,
                        "Sindhu",
                        "sindhu@company.com",
                        68000,
                        10,
                        4
                ),

                new Employee(
                        3,
                        "Pooja",
                        "pooja@company.com",
                        82000,
                        20,
                        5
                ),

                new Employee(
                        4,
                        "Rahul",
                        "rahul@company.com",
                        95000,
                        20,
                        5
                )
        );

        System.out.println("Average salary by department:");
        System.out.println(averageSalaryByDepartment(employees));

        System.out.println("\nTop 3 earners:");
        System.out.println(topThreeEarners(employees));

        System.out.println("\nEmployees by department:");
        System.out.println(employeesByDepartment(employees));
    }
}