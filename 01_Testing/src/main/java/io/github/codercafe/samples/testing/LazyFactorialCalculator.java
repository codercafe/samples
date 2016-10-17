package io.github.codercafe.samples.testing;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class LazyFactorialCalculator {

    private AtomicLong value = new AtomicLong();
    private final Random random = new Random();

    public void calculate(long number) {
        new Thread(() -> {
            long result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
                try {
                    Thread.sleep(50 + random.nextInt(500)); // let's wait
                } catch (InterruptedException ignored) {
                }

            }
            value.set(result);
        }).start();
    }

    public long getResult() {
        return value.get();
    }
}

