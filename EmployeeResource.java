import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private static EmployeeService service =
            new EmployeeService();

    @GET
    public List<Employee> getEmployees() {
        return service.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    public Employee getEmployee(@PathParam("id") int id) {
        return service.getEmployee(id);
    }

    @POST
    public Response addEmployee(Employee employee) {

        Employee addedEmployee = service.addEmployee(employee);

        return Response.status(Response.Status.CREATED)
                .entity(addedEmployee)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(
            @PathParam("id") int id,
            Employee employee) {

        employee.setId(id);

        Employee updatedEmployee =
                service.updateEmployee(employee);

        return Response.ok(updatedEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {

        service.deleteEmployee(id);

        return Response.noContent().build();
    }
}