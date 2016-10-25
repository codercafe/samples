package io.github.codercafe.samples.testing

import groovy.transform.EqualsAndHashCode
import org.junit.Test

import static java.util.UUID.randomUUID
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.MatcherAssert.assertThat;

class Coercion {

    @Test
    public void typeCoercion() {
        // SoftwareEngineer via implicit and URL via explicit Coercion
        SoftwareEngineer engineer = ["Frank", 1234567890, ["https://github.com/frankwis"] as URL]
        assertThat engineer, is(equalTo(new SoftwareEngineer("Frank", 1234567890, new URL("https://github.com/frankwis"))))
    }

    @Test
    public void mocking() {
        def engineer = ["John Doe", 1234] as SoftwareEngineer

        def closureCoercedService = { UUID id -> engineer } as YellowPages
        assertThat closureCoercedService.getEngineer(randomUUID()), is(equalTo(new SoftwareEngineer("John Doe", 1234)))

        def mapCoercedService = [getEngineer: { UUID id -> engineer }] as YellowPages
        assertThat mapCoercedService.getEngineer(randomUUID()), is(equalTo(new SoftwareEngineer("John Doe", 1234)))
    }

    @Test
    public void groovyBean() {
        // explicit
        def engineers1 = [participants: [["John Doe", 1234], ["Jane Doe", 4321]],
                          tutors      : [["Frank", 5556667778], ["Lars", 1112223334]]] as Engineers

        // implicit
        Engineers engineers2 = [participants: [["John Doe", 1234], ["Jane Doe", 4321]],
                                tutors      : [["Frank", 5556667778], ["Lars", 1112223334]]]
        assertThat engineers1, is(equalTo(engineers2))
    }

    @EqualsAndHashCode
    class Engineers {
        SoftwareEngineer[] participants;
        SoftwareEngineer[] tutors;
    }
}
