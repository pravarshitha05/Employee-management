import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryInMemoryImplTest {

    @Test
    void testAddEmployee() {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();

        Employee employee =
                new Employee(1, "Pravarshitha",
                        "pravarshitha@company.com",
                        75000,
                        10);

        Employee result = repo.add(employee);

        assertEquals(employee, result);
    }

    @Test
    void testFindById() {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();

        Employee employee1 =new Employee(1, "Pravarshitha","pravarshitha@company.com",75000,10);
        Employee employee2 =new Employee(2, "Sindhu","sindhu@company.com", 68000, 10);

        Employee employee3 =new Employee(3, "Pooja","pooja@company.com",82000, 20);


        repo.add(employee1);
        repo.add(employee2);
        repo.add(employee3);

        Employee result = repo.findById(2);

        assertEquals(employee2, result);
    }

    @Test
    void testUpdateEmployee() {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();

        Employee employee =
                new Employee(1, "Pravarshitha",
                        "pravarshitha@company.com",
                        75000,
                        10);

        repo.add(employee);

        Employee updatedEmployee =
                new Employee(1, "Pravarshitha",
                        "pravarshithareddy@company.com",
                        80000,
                        10);

        Employee result = repo.update(updatedEmployee);

        assertEquals("pravarshithareddy@company.com", result.getEmail());
        assertEquals(80000, result.getSalary());
    }

    @Test
    void testDeleteEmployee() {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();

        Employee employee =
                new Employee(1, "Pravarshitha",
                        "pravarshitha@company.com",
                        75000,
                        10);

        repo.add(employee);

        boolean result = repo.delete(1);

        assertTrue(result);

        assertNull(repo.findById(1));
    }

    @Test
    void testFindAll() {

        EmployeeRepositoryInMemoryImpl repo =
                new EmployeeRepositoryInMemoryImpl();

        repo.add(new Employee(1, "Pravarshitha",
                "pravarshitha@company.com",
                75000, 10));

        repo.add(new Employee(2, "Sindhu",
                "sindhu@company.com",
                68000, 10));

        List<Employee> employees = repo.findAll();

        assertEquals(2, employees.size());
    }
}