package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class represents a simple calculator for testing
 */
public class SpelloutCalculator {

    private final RuleBasedNumberFormat spelloutFormatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public String add(int x, int y) {
        return spelloutFormatter.format(x + y);
    }

    public String subtract(int minuend, int sutrahend) {
        return spelloutFormatter.format(minuend - sutrahend);
    }

    public String multiply(int x, int y) {
        return spelloutFormatter.format(x + y);
    }

    public String divide(int divident, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by null");
        }
        return spelloutFormatter.format((double) divident / divisor);
    }

    public List<String> primeFactorization(int number) {
        int rest = number;
        List<String> factors = new ArrayList<>();
        for (int i = 2; i <= rest / i; i++) {
            while (rest % i == 0) {
                factors.add(spelloutFormatter.format(i));
                rest /= i;
            }
        }

        if (rest > 1) {
            factors.add(spelloutFormatter.format(rest));
        }
        return factors;
    }
}
