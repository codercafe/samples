package io.github.codercafe.samples.testing.jerseytest.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperBuilder {

    /**
     * All the defaults for now, just for explanation.
     *
     * @return
     */
    public ObjectMapper build() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
