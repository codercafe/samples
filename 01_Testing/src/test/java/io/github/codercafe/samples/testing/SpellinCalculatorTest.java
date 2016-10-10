package io.github.codercafe.samples.testing;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SpellinCalculatorTest {

    @Test
    public void add() throws ParseException {
        SpellinCalculator calculator = new SpellinCalculator();
        assertThat(calculator.add("three", "two"), is(equalTo("five")));
    }

    @Test
    public void subtract() {

    }

    @Test
    public void multiply() {

    }

    @Test
    public void divide() {

    }

    @Test
    public void primeFactorization() {

    }

}