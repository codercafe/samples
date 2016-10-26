package io.github.codercafe.samples.testing.jerseytest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.github.codercafe.samples.testing.jerseytest.json.DefaultObjectMapper;
import io.github.codercafe.samples.testing.jerseytest.json.ObjectMapperBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloWorldTest extends JerseyTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig() {
            {
                register(DefaultObjectMapper.class);
                register(new HelloWorld());
            }
        };
    }

    @Override
    protected void configureClient(ClientConfig config) {
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        jsonProvider.setMapper(new ObjectMapperBuilder().build());
        config.register(jsonProvider);
    }

    @Test
    public void testHelloWorldIndent() throws Exception {
        String result = target("hello").request().get(String.class);
        logger.info("Response: {}", result);
    }

    @Test
    public void testHelloWorld() throws Exception {
        Hello hello = target("hello").request().get(Hello.class);
        assertThat(hello.hello, is(equalTo("World")));
    }
}
