package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

public class LazyFibonacciCalculator {

    private final RuleBasedNumberFormat spelloutFormatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);
    private static Logger logger = LoggerFactory.getLogger(LazyFibonacciCalculator.class);

    private AtomicReference<String> value = new AtomicReference<>();

    public void calculate(int number) {
        new Thread(() -> {
            if (number == 1 || number == 2) {
                value.set(spelloutFormatter.format(1));
            }
            int lastFibonacci = 1, currentFibonacci = 1, result = 1;
            for (int i = 3; i <= number; i++) {
                result = lastFibonacci + currentFibonacci;
                lastFibonacci = currentFibonacci;
                currentFibonacci = result;

                // let's be lazy
                try {
                    Thread.sleep(50 + Math.round(1000 * Math.random()));
                } catch (InterruptedException ex) {
                    logger.warn("Couldn't be lazy.", ex);
                }

            }
            value.set(spelloutFormatter.format(result));
        }).start();
    }

    public String getResult() {
        return value.get();
    }
}