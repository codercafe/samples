package io.github.codercafe.samples.testing.jerseytest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/helloService")
@Produces(APPLICATION_JSON)
public class HelloWorldWithService {

    private final HelloWorldService service;

    public HelloWorldWithService(HelloWorldService service) {
        this.service = service;
    }

    @GET
    public Hello hello(UUID id) {
        return service.get(id);
    }
}