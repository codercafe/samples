package io.github.codercafe.samples.testing;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.NullPointerTester;
import com.google.common.testing.SerializableTester;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * This class is showcasing Guava's test utils for educational purpose.
 */
public class StringPoint3DTest {

    @Test
    public void nullPointerException() {
        new NullPointerTester()
                .setDefault(String.class, "null")
                .testAllPublicConstructors(StringPoint3D.class);
    }

    @Test
    public void serializable() throws ParseException {
        SerializableTester.reserializeAndAssert(new StringPoint3D("one", "two", "three"));
    }

    @Test
    public void guavaEqualsTester() throws ParseException {

        StringPoint3D point = new StringPoint3D(3, 2, 1);

        assertFalse(point.equals(null));                        // object against null returns false
        assertFalse(point.equals(new WordCalculator()));        // incompatible class returns false
        assertFalse(point.equals(new StringPoint3D(1, 2, 3)));  // different parameters result in inequality
        assertTrue(point.equals(point));                        // object against itself returns true
        assertTrue(point.equals(new StringPoint3D(3, 2, 1)));   // same parameters result in equality
        assertEquals(point.hashCode(), new StringPoint3D(3, 2, 1).hashCode()); // hash code matches on equality

        // How about String constructor (e.g. new StringPoint3D("three", "two", "one") )?
        new EqualsTester()
                .addEqualityGroup(new StringPoint3D(1, 2, 3),
                        new StringPoint3D("one", "two", "three"))
                .addEqualityGroup(new StringPoint3D(0, 0, 0),
                        StringPoint3D.ZERO)
                .testEquals();
    }

}


