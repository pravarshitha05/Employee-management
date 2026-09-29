import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static void main(String[] args) {

        String url = "http://localhost:8080/";

        ResourceConfig config = new ResourceConfig();

        config.register(EmployeeResource.class);
        config.register(EmployeeNotFoundExceptionMapper.class);
        config.register(DuplicateEmployeeExceptionMapper.class);
        config.register(JacksonFeature.class);

        HttpServer server =
                GrizzlyHttpServerFactory.createHttpServer(
                        URI.create(url),
                        config
                );

        System.out.println(
                "Server started at http://localhost:8080/"
        );

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
