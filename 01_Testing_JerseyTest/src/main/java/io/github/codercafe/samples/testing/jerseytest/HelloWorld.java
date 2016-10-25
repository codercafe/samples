package io.github.codercafe.samples.testing.jerseytest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/hello")
@Produces(APPLICATION_JSON)
public class HelloWorld {

    @GET
    public Hello hello() {
        return new Hello("World");
    }
}