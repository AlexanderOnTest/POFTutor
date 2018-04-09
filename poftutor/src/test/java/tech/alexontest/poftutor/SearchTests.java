package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;
import tech.alexontest.poftutor.steps.SearchNotFoundSteps;
import tech.alexontest.poftutor.steps.WidgetSteps;

class SearchTests extends AbstractSingleWebDriverTest {
    @Inject
    private HomePageSteps homePageSteps;

    @Inject
    private WidgetSteps widgetSteps;

    @Inject
    private SearchNotFoundSteps searchNotFoundSteps;

    @BeforeEach
    void setupHomepage() {
        homePageSteps.loadHomePage();
    }

    @Test
    @DisplayName("Search widget search using the button returns search page")
    void searchWidgetFunctionsCorrectlyUsingButton() {
        final String searchTerm1 = "JUnit 5";
        widgetSteps.search(searchTerm1)
                .verifyPageTitle(searchTerm1)
                .verifyURL(searchTerm1);
    }

    @Test
    @DisplayName("Search widget search submitting with enter returns search page")
    void searchWidgetFunctionsCorrectlyUsingEnter() {
        final String searchTerm2 = "dependency injection";
        widgetSteps.searchByEnter(searchTerm2)
                .verifyURL(searchTerm2)
                .verifyPageTitle(searchTerm2);
    }

    @Test
    @DisplayName("No results page is displayed correctly.")
    void searchWithNoResultsWorks() {
        final String notFoundSearchTerm = "notaword";
        widgetSteps.search(notFoundSearchTerm);
        searchNotFoundSteps.verifyPageTitle()
                .verifyURL(notFoundSearchTerm)
                .verifyInputFieldContent(notFoundSearchTerm);
    }
}
