package io.github.codercafe.samples.testing;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class NumberCalculatorTest {

    @Rule
    public final ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() {

    }

    @Test
    public void add() {
        NumberCalculator calculator = new NumberCalculator();
        assertEquals("fifteen", calculator.add(12, 3));
        assertEquals("fourteen", calculator.add(10, 4));
        assertEquals("minus seven", calculator.add(7, -14));
    }

    @Test
    public void subtract() {

    }

    @Test
    public void multiply() {

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

    /**
     *
     * @param items
     * @return A Matcher all items in arbitrary order and correct number of occurrences.
     */
    private <T> Matcher<Collection<T>> hasAllItems(final T... items) {
        return new TypeSafeMatcher<Collection<T>>() {

            @Override
            public boolean matchesSafely(final Collection<T> item) {
                if (items.length != item.size()) {
                    return false;
                }

                Map<T, Long> counts = item
                        .stream()
                        .collect(groupingBy(element -> element, counting()));
                for (T value : counts.keySet()) {
                    if (Collections.frequency(item, value) != counts.get(value)) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("a collection containing ").appendValue(items).appendText( " in arbitrary order");
            }
        };
    }
}