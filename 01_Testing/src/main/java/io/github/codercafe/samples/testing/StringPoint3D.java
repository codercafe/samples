package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Objects;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class is for educational purpose only and not intended for proper usage ;-) .
 */
public class StringPoint3D implements Serializable {

    public static final StringPoint3D ZERO = new StringPoint3D(0, 0, 0);
    public final String x, y, z;
    private final RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public StringPoint3D(String x, String y, String z) throws ParseException {
        requireNonNull(x, y, z);
        parsable(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public StringPoint3D(int x, int y, int z) {
        this.x = format(x);
        this.y = format(y);
        this.z = format(z);
    }

    private String format(int number) {
        return formatter.format(number);
    }

    private void parsable(String x, String y, String z) throws ParseException {
        parsable(x);
        parsable(y);
        parsable(z);
    }

    private void parsable(String number) throws ParseException {
        formatter.parse(number);
    }

    private void requireNonNull(String x, String y, String z) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        Objects.requireNonNull(z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringPoint3D that = (StringPoint3D) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(z, that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
