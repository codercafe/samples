package io.github.codercafe.samples.testing.jerseytest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hello {

    public final String hello;

    @JsonCreator
    public Hello(@JsonProperty("hello") String hello) {
        this.hello = hello;
    }
}
