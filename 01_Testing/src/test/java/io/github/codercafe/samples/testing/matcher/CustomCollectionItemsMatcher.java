package io.github.codercafe.samples.testing.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * This class implements a matcher which verifies that a {@link Collection} of Strings holds all given items in
 * arbitrary order and number of occurrences.
 */
public final class CustomCollectionItemsMatcher extends TypeSafeMatcher<Collection<String>> {

    private final String[] items;

    private CustomCollectionItemsMatcher(String... items) {
        this.items = items;
    }

    public static Matcher<Collection<String>> hasAllItems(String... items) {
        return new CustomCollectionItemsMatcher(items);
    }

    @Override
    protected boolean matchesSafely(Collection<String> item) {
        if (items.length != item.size()) {
            return false;
        }

        Map<String, Long> counts = item.stream()
                .collect(groupingBy(element -> element, counting()));
        return !counts.keySet().stream()
                .anyMatch(value -> Collections.frequency(item, value) != counts.get(value));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection containing ")
                .appendValue(items)
                .appendText(" in arbitrary order");
    }
}
