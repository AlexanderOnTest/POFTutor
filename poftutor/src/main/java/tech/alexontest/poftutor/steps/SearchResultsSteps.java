package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.pages.SearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsSteps {
    private SearchResultsPage resultsPage;

    private final WebDriver driver;

    @Inject
    SearchResultsSteps(final SearchResultsPage resultsPage,
                       final WebDriver driver) {
        this.resultsPage = resultsPage;
        this.driver = driver;
    }

    public SearchResultsSteps verifyURL(final String searchText) {
        assertThat(driver.getCurrentUrl())
                .as("SearchPage URL is incorrect.")
                .isEqualTo(resultsPage.getPageUrl(searchText));
        return this;
    }

    public SearchResultsSteps verifyPageTitle(final String searchText) {
        assertThat(resultsPage.getPageTitle())
                .as("Page Title is not as expected.")
                .isEqualTo(String.format("Search Results for: %s", searchText));
        return this;
    }
}
