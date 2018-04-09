package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.pages.NoSearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchNotFoundSteps {
    private final NoSearchResultsPage noSearchResultsPage;

    private final WebDriver driver;

    @Inject
    public SearchNotFoundSteps(final NoSearchResultsPage noSearchResultsPage,
                               final WebDriver driver) {
        this.noSearchResultsPage = noSearchResultsPage;
        this.driver = driver;
    }

    public SearchNotFoundSteps verifyURL(final String searchText) {
        assertThat(driver.getCurrentUrl())
                .as("SearchPage URL is incorrect.")
                .isEqualTo(noSearchResultsPage.getPageUrl(searchText));
        return this;
    }

    public SearchNotFoundSteps verifyPageTitle() {
        assertThat(noSearchResultsPage.getPageTitle())
                .as("Page Title is not as expected.")
                .isEqualTo("Nothing Found");
        return this;
    }

    public SearchNotFoundSteps verifyInputFieldContent(final String searchText) {
        assertThat(noSearchResultsPage.getMainSearchFieldText())
                .as("Search field does not contain the searched for term")
                .isEqualTo(searchText);
        return this;
    }
}
