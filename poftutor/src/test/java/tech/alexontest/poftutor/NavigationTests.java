package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.alexontest.poftutor.infrastructure.AbstractSingleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;

@Tag("Navigation")
class NavigationTests extends AbstractSingleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @ParameterizedTest(name = "{0} returns 'https://alexanderontesting.com' as expected.")
    @CsvSource({
            "https://alexanderontesting.com/",
            //"http://alexanderontesting.com/", //TODO fix http redirection w/o breaking others
            "https://www.alexanderontesting.com/",
            "http://www.alexanderontesting.com/",
            "https://alexontest.tech/",
            "http://alexontest.tech/",
            "https://www.alexontest.tech/",
            "http://www.alexontest.tech/",
    })

    void allUrlsLeadToHere(final String urlToTest) {
        homePageSteps
                .attemptToLoadHomePageFromUrl(urlToTest)
                .assertThatReportedUrlIsCorrect();
    }
}
