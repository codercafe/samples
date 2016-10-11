package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class represents a simple calculator for testing. It takes {@link Integer}s as input and returns a word
 * representation of the result (i.e. {@link String}.
 */
public class NumberCalculator {

    private final RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public String add(int x, int y) {
        return format(x + y);
    }

    public String subtract(int minuend, int subtrahend) {
        return format(minuend - subtrahend);
    }

    public String multiply(int x, int y) {
        return format(x + y);
    }

    public String divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by null");
        }
        return format((double) dividend / divisor);
    }

    public List<String> primeFactorization(int number) {
        int rest = number;
        List<String> factors = new ArrayList<>();
        for (int i = 2; i <= rest / i; i++) {
            while (rest % i == 0) {
                factors.add(format(i));
                rest /= i;
            }
        }

        if (rest > 1) {
            factors.add(format(rest));
        }
        return factors;
    }

    private String format(Object number) {
        return formatter.format(number);
    }
}
