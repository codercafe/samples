package io.github.codercafe.samples.testing

import groovy.transform.EqualsAndHashCode
import org.junit.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.MatcherAssert.assertThat;

class Coercion {

    @Test
    public void typeCoercion() {
        // SoftwareEngineer via implicit and URL via explicit Coercion
        SoftwareEngineer engineer = ["John Doe", 1234567890, ["https://github.com/frankwis"] as URL]
        assertThat engineer, is(equalTo(new SoftwareEngineer("Frank", 1234567890, new URL("https://github.com/frankwis"))))
    }

    @Test
    public void mockingViaCoercion() {
        def person = ["John Doe", 1234] as SoftwareEngineer
        def mapCoercedService = [getEngineer: { UUID id -> person }] as YellowPages
        def closureCoercedService = { UUID id -> person } as YellowPages
        assert mapCoercedService.getEngineer(UUID.randomUUID()) == new SoftwareEngineer("John Doe", 1234)
        assert closureCoercedService.getEngineer(UUID.randomUUID()) == new SoftwareEngineer("John Doe", 1234)
    }

    @Test
    public void groovyBean() {
        // explicit
        def class1 = [students: [["John Doe", 1234], ["Jane Doe", 4321]],
                      teacher: [["Frank", 5556667778], ["Lars", 1112223334]]] as Class

        // implicit
        Class class2 = [students: [["John Doe", 1234], ["Jane Doe", 4321]],
                        teacher: [["Frank", 5556667778], ["Lars", 1112223334]]]
        assert class1 == class2
    }

    @EqualsAndHashCode
    class Class {
        SoftwareEngineer[] students;
        SoftwareEngineer[] teacher;
    }

}
