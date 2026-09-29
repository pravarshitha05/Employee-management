import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmployeeNotFoundExceptionMapper
        implements ExceptionMapper<EmployeeNotFoundException> {

    @Override
    public Response toResponse(
            EmployeeNotFoundException exception) {

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
