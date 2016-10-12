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
public class StringVector3DTest {

    @Test
    public void nullPointerException() {
        new NullPointerTester()
                .setDefault(String.class, "null")
                .testAllPublicConstructors(StringVector3D.class);
    }

    @Test
    public void serializable() throws ParseException {
        SerializableTester.reserializeAndAssert(new StringVector3D("one", "two", "three"));
    }

    @Test
    public void guavaEqualsTester() throws ParseException {

        StringVector3D vector1 = new StringVector3D(3, 2, 1);

        assertFalse(vector1.equals(null));                          // object against null returns false
        assertFalse(vector1.equals(new WordCalculator()));          // incompatible class returns false
        assertFalse(vector1.equals(new StringVector3D(1, 2, 3)));   // different parameters result in inequality
        assertTrue(vector1.equals(vector1));                        // object against itself returns true
        assertTrue(vector1.equals(new StringVector3D(3, 2, 1)));    // same parameters result in equality
        assertEquals(vector1.hashCode(), new StringVector3D(3, 2, 1).hashCode()); // hash code matches on equality


        // How about String constructor (e.g. new StringVector3D("three", "two", "one") )?
        new EqualsTester()
                .addEqualityGroup(new StringVector3D(1, 2, 3),
                        new StringVector3D("one", "two", "three"))
                .addEqualityGroup(new StringVector3D(0, 0, 0),
                        StringVector3D.ZERO)
                .testEquals();
    }

}


