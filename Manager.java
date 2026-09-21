import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private List<Employee> reports;

    public Manager(int id, String name, String email, double salary, int departmentId) {
        super(id, name, email, salary, departmentId);
        this.reports = new ArrayList<>();
    }

    public List<Employee> getReports() {
        return reports;
    }

    public void setReports(List<Employee> reports) {
        this.reports = reports;
    }

    public void addReport(Employee employee) {
        this.reports.add(employee);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", salary=" + getSalary() +
                ", departmentId=" + getDepartmentId() +
                ", reportsCount=" + reports.size() +
                '}';
    }
}
