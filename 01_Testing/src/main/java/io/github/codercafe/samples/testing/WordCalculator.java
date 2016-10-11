package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class represents a simple calculator taking words as input and returns numbers (i.e. {@link Long} and
 * {@link Double}).
 */
public class WordCalculator {

    private final RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public long add(String x, String y) throws ParseException {
        return parseInt(x) + parseInt(y);
    }

    public long subtract(String minuend, String subtrahend) throws ParseException {
        return parseInt(minuend) - parseInt(subtrahend);
    }


    public long multiply(String x, String y) throws ParseException {
        return parseInt(x) * parseInt(y);
    }

    public double divide(String dividend, String divisor) throws ParseException {
        return (double) parseInt(dividend) / parseInt(divisor);
    }

    public List<Long> primeFactorization(int number) {
        long rest = number;
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= rest / i; i++) {
            while (rest % i == 0) {
                factors.add(i);
                rest /= i;
            }
        }

        if (rest > 1) {
            factors.add(rest);
        }
        return factors;
    }

    private int parseInt(String number) throws ParseException {
        return formatter.parse(number).intValue();
    }
}