package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.pages.SearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;

public class SearchResultsSteps {
    private SearchResultsPage resultsPage;

    private final WebDriver driver;

    private final TestConfiguration testConfiguration;

    @Inject
    SearchResultsSteps(final SearchResultsPage resultsPage,
                       final WebDriver driver,
                       final TestConfiguration testConfiguration) {
        this.resultsPage = resultsPage;
        this.driver = driver;
        this.testConfiguration = testConfiguration;
    }

    public SearchResultsSteps verifyURL(final String searchText) {
        assertThat(driver.getCurrentUrl())
                .as("SearchPage URL is incorrect.")
                .isEqualTo(resultsPage.getPageUrl(searchText));
        return this;
    }

    public SearchResultsSteps verifyPageTitle(final String searchText) {
        await(String.format("Search results failed to load for %s", searchText))
                .atMost(testConfiguration.getWaitTimeout())
                .ignoreExceptionsMatching(anyOf(
                        instanceOf(NoSuchElementException.class),
                        instanceOf(StaleElementReferenceException.class)))
                .untilAsserted(() -> assertThat(resultsPage.getPageTitle())
                        .as("Page Title is not as expected.")
                        .isEqualTo(String.format("Search Results for: %s", searchText)));
        return this;
    }
}
