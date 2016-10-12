package io.github.codercafe.samples.testing;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static io.github.codercafe.samples.testing.matcher.CustomCollectionItemsMatcher.hasAllItems;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class NumberCalculatorTest {

    @Rule
    public final ExpectedException expected = ExpectedException.none();

    @Test
    public void add() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals("fifteen", calculator.add(12, 3));
        assertEquals("fourteen", calculator.add(10, 4));
        assertEquals("minus seven", calculator.add(7, -14));
    }

    @Test
    public void subtract() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals("nine", calculator.subtract(12, 3));
        assertEquals("minus fourteen", calculator.subtract(-10, 4));
        assertEquals("twenty-one", calculator.subtract(7, -14));
    }

    @Test
    public void multiply() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals("thirty-six", calculator.multiply(12, 3));
        assertEquals("minus forty", calculator.multiply(-10, 4));
        assertEquals("minus ninety-eight", calculator.multiply(7, -14));
    }

    @Test
    public void divide() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals("four", calculator.divide(12, 3));
        assertEquals("two point five", calculator.divide(10, 4));
        assertEquals("minus two point five", calculator.divide(-10, 4));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByNull() {
        NumberCalculator calculator = new NumberCalculator();
        calculator.divide(12, 0);
    }

    @Test
    public void divideByNullTestRule() {
        NumberCalculator calculator = new NumberCalculator();
        expected.expect(ArithmeticException.class);
        expected.expectMessage("Cannot divide by null");
        calculator.divide(12, 0);
    }

    @Ignore("Order does matter")
    @Test
    public void primeFactorization() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals(calculator.primeFactorization(35), Arrays.asList("five", "seven"));
        assertEquals(calculator.primeFactorization(35), Arrays.asList("seven", "five"));
    }

    @Test
    public void divideWithMatchers() {
        NumberCalculator calculator = new NumberCalculator();
        assertThat(calculator.divide(12, 3), is(equalTo("four")));
        assertThat(calculator.divide(-10, 4), is(equalTo("minus two point five")));
    }

    @Test
    public void primeFactorizationWithMatchers() {
        NumberCalculator calculator = new NumberCalculator();
        assertThat(calculator.primeFactorization(350), hasItems("two", "five", "seven"));
    }

    @Test
    public void primeFactorizationCustomMatcher() {
        NumberCalculator calculator = new NumberCalculator();
        assertThat(calculator.primeFactorization(350), hasAllItems("two", "five", "five", "seven"));
        assertThat(calculator.primeFactorization(350), not(hasAllItems("two", "five", "seven")));
    }
}