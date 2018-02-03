package tech.alexontest.poftutor;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Navigation")
class NavigationTests extends AbstractTest {

    private final String homePageURL = "https://alexanderontesting.com/";

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "https://alexanderontesting.com/, Homepage URL displays correctly",
            //"http://alexanderontesting.com/, http is directed to https", TODO fix http redirection w/o breaking others
            "https://www.alexanderontesting.com/, https://www.alexanderontesting.com/ directs here",
            "http://www.alexanderontesting.com/, http://www.alexanderontesting.com/ directs here",
            "https://alexontest.tech/, https://alexontest.tech is redirected here",
            "http://alexontest.tech/, http://alexontest.tech is redirected here",
            "https://www.alexontest.tech/, https://www.alexontest.tech is redirected here",
            "http://www.alexontest.tech/, http://www.alexontest.tech is redirected here"
    })

    void allUrlsLeadToHere(final String urlToTest) {
        assertThat(getDriver(urlToTest).getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}
