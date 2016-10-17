package io.github.codercafe.samples.testing;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.text.ParseException;
import java.util.Objects;

import static com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT;
import static com.ibm.icu.util.ULocale.ENGLISH;

/**
 * This class is for educational purpose only and not intended for proper usage ;-) .
 */
public class StringLine3D {

    public final StringPoint3D start, end;
    private final RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ENGLISH, SPELLOUT);

    public StringLine3D(StringPoint3D start, StringPoint3D end) {
        Objects.nonNull(start);
        Objects.nonNull(end);
        this.start = start;
        this.end = end;
    }

    public StringLine3D revert() {
        return new StringLine3D(end, start);
    }

    public double length() {
        try {
            double x = parse(start.x) - parse(end.x);
            double y = parse(start.y) - parse(end.y);
            double z = parse(start.z) - parse(end.z);
            return Math.sqrt(x*x + y*y + z*z);
        } catch (ParseException ex) {
            throw new IllegalStateException("Could not parse value of a given vector. Should not happen!", ex);
        }
    }

    private double parse(String value) throws ParseException {
        return formatter.parse(value).doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringLine3D that = (StringLine3D) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
