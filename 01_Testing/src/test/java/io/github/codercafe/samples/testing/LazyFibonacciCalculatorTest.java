package io.github.codercafe.samples.testing;

import org.awaitility.Awaitility;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.to;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class LazyFibonacciCalculatorTest {

    static {
        Awaitility.setDefaultTimeout(20, TimeUnit.SECONDS);
        Awaitility.setDefaultPollInterval(1, TimeUnit.SECONDS);
        Awaitility.setDefaultPollDelay(500, TimeUnit.MILLISECONDS);
    }

    @Ignore("Bad pattern: Sleep duration unforeseeable.")
    @Test
    public void calculateWithThreadSleep() throws InterruptedException {
        LazyFibonacciCalculator calculator = new LazyFibonacciCalculator();
        calculator.calculate(20);
        assertNull(calculator.getResult());
        Thread.sleep(15000);
        assertThat(calculator.getResult(), is(equalTo("six thousand seven hundred sixty-five")));
    }

    @Test
    public void calculateWithAwaitility() {
        LazyFibonacciCalculator calculator = new LazyFibonacciCalculator();
        calculator.calculate(20);
        await().until(calculator::getResult, is(equalTo("six thousand seven hundred sixty-five"))); // callable
        await().untilCall(to(calculator).getResult(), is(equalTo("six thousand seven hundred sixty-five"))); // proxy
    }
}