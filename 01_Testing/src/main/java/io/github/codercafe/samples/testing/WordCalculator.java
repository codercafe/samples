package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class represents a simple calculator taking words as input and returns numbers (i.e. int and double).
 * The calculator is neither mathematically accurate (e.g. Integer overflow etc.) nor threadsafe.
 */
public class WordCalculator {

    private final RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public int add(String x, String y) throws ParseException {
        return parseInt(x) + parseInt(y);
    }

    public int subtract(String minuend, String subtrahend) throws ParseException {
        return parseInt(minuend) - parseInt(subtrahend);
    }

    public int multiply(String x, String y) throws ParseException {
        return parseInt(x) * parseInt(y);
    }

    public double divide(String dividend, String divisor) throws ParseException {
        return (double) parseInt(dividend) / parseInt(divisor);
    }

    public List<Integer> primeFactorization(int number) {
        int rest = number;
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= rest / i; i++) {
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

    public int parseInt(String number) throws ParseException {
        return formatter.parse(number).intValue();
    }
}