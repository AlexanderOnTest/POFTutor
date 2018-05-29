package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.pages.NoSearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;

public class SearchNotFoundSteps {
    private final NoSearchResultsPage noSearchResultsPage;

    private final WebDriver driver;

    private TestConfiguration testConfiguration;

    @Inject
    public SearchNotFoundSteps(final NoSearchResultsPage noSearchResultsPage,
                               final WebDriver driver,
                               final TestConfiguration testConfiguration) {
        this.noSearchResultsPage = noSearchResultsPage;
        this.driver = driver;
        this.testConfiguration = testConfiguration;
    }

    public SearchNotFoundSteps verifyURL(final String searchText) {
        assertThat(driver.getCurrentUrl())
                .as("SearchPage URL is incorrect.")
                .isEqualTo(noSearchResultsPage.getPageUrl(searchText));
        return this;
    }

    public SearchNotFoundSteps verifyPageTitle() {
        await("Unsuccessful search results failed to load.")
                .atMost(testConfiguration.getWaitTimeout())
                .ignoreExceptionsMatching(anyOf(
                        instanceOf(NoSuchElementException.class),
                        instanceOf(StaleElementReferenceException.class)))
                .untilAsserted(() -> assertThat(noSearchResultsPage.getPageTitle())
                .as("Page Title is not as expected.")
                .isEqualTo("Nothing Found"));
        return this;
    }

    public SearchNotFoundSteps verifyInputFieldContent(final String searchText) {
        assertThat(noSearchResultsPage.getMainSearchFieldText())
                .as("Search field does not contain the searched for term")
                .isEqualTo(searchText);
        return this;
    }
}
