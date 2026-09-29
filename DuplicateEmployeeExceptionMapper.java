import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateEmployeeExceptionMapper
        implements ExceptionMapper<DuplicateEmployeeException> {

    @Override
    public Response toResponse(
            DuplicateEmployeeException exception) {

        return Response
                .status(Response.Status.CONFLICT)
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
