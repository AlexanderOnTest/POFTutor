package tech.alexontest.poftutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Content")
class HomePageTests extends AbstractTest {
    private HomePage homePage;

    @BeforeEach
    void setupHomepage() {
        homePage = new PfHomePage(getDriver("https://alexanderontesting.com/"));                             //Page Factory implementation
    }

    @Test
    @DisplayName("The correct title is reported")
    void titleIsCorrect() {
        assertThat(homePage.getPageTitle())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    @Test
    @DisplayName("5 Widgets appear on the Homepage")
    void pageContainsFiveWidgets() {
        assertThat(homePage.getWidgets())
                .size()
                .isEqualTo(5);
    }

    @Test
    @DisplayName("No more than 5 posts are linked on the Homepage")
    void pageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(5);
    }
}