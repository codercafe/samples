package io.github.codercafe.samples.testing.jerseytest.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Priority;
import javax.ws.rs.Consumes;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
@Priority(Priorities.ENTITY_CODER - 1)
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class DefaultObjectMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper defaultObjectMapper = new ObjectMapperBuilder().build();

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
}
