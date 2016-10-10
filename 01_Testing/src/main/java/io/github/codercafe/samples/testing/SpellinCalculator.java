package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.text.ParseException;
import java.util.List;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class represents a simple calculator taking words as input.
 */
public class SpellinCalculator {

    private final RuleBasedNumberFormat spellFormatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);
    private final SpelloutCalculator spelloutCalculator = new SpelloutCalculator();

    public String add(String x, String y) throws ParseException {
        return spelloutCalculator.add(spellFormatter.parse(x).intValue(), spellFormatter.parse(y).intValue());
    }

    public String subtract(String minuend, String sutrahend) throws ParseException {
        return spelloutCalculator.subtract(spellFormatter.parse(minuend).intValue(),
                spellFormatter.parse(sutrahend).intValue());
    }

    public String multiply(String x, String y) {
        return null;
    }

    public String divide(String divident, String divisor) {
        return null;
    }

    public List<String> primeFactorization(String number) {
        return null;
    }
}