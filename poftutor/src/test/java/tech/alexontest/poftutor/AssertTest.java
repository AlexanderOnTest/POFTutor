package tech.alexontest.poftutor;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AssertTest {
    private final String expected = "Hello";
    private final String sameAsExpected = "Hello";
    private final String actual = "HELLO";
    private final String different = "Goodbye";
    private final List<String> expectedList = Arrays.asList(expected, expected, expected, expected);
    private final List<String> actualList = Arrays.asList(expected, sameAsExpected, actual, different);

    @Test
    void assertEqualsStringComparisonTest() {
        assertAll("JUnit assertEquals String comparisons",
                () -> assertEquals(expected,expected, "Same objects should never fail"),
                () -> assertEquals(expected, sameAsExpected, "Different Strings with same content also pass."),
                () -> assertEquals(expected, actual, "Strings differing in case should fail."),
                () -> assertEquals(expected, different, "Strings differing in content should always fail.")
        );
    }

    @Test
    void assertLinesMatchStringComparisonTest() {
        assertLinesMatch(expectedList, actualList);
    }

    @Test
    void assertTrueIgnoreCaseStringComparisonTest() {
        assertAll("JUnit assertTrue (equalsIgnoreCase(..)) String comparisons",
                () -> assertTrue(expected.equalsIgnoreCase(expected), "Same objects should never fail"),
                () -> assertTrue(expected.equalsIgnoreCase(sameAsExpected), "Different Strings with same content should pass."),
                () -> assertTrue(expected.equalsIgnoreCase(actual), "Strings differing in case should not fail now."),
                () -> assertTrue(expected.equalsIgnoreCase(different), "Strings differing should always fail.") );
    }

    @Test
    void assertTrueIgnoreCaseWithExplanationStringComparisonTest() {
        assertAll("JUnit assertTrue (equalsIgnoreCase(..)) String comparisons with explanation",
                () -> assertTrueIgnoreCaseWithExplanation("Same objects",expected, expected),
                () -> assertTrueIgnoreCaseWithExplanation("Same contents", expected, sameAsExpected),
                () -> assertTrueIgnoreCaseWithExplanation("Different Case", expected, actual),
                () -> assertTrueIgnoreCaseWithExplanation("Different Words", expected, different)
        );
    }

    private void assertTrueIgnoreCaseWithExplanation(final String description, final String expectedString, final String actualString) {
        assertTrue(expectedString.equalsIgnoreCase(actualString),
                () -> String.format("%1$s -> The expected is: '%2$s' while the actual is: '%3$s'", description, expectedString, actualString));
    }

    @Test
    void assertjTest() {
        assertAll("String comparison using Joel Costigliola's assertj library.",
            () -> assertThat(expected).isEqualToIgnoringCase(expected),
            () -> assertThat(sameAsExpected).isEqualToIgnoringCase(expected),
            () -> assertThat(actual).isEqualToIgnoringCase(expected),
            () -> assertThat(different).isEqualToIgnoringCase(expected) );
        }

    @Test
    void assertjListTest() {
        assertThat(actualList)
                .containsSequence(expectedList);
    }
}