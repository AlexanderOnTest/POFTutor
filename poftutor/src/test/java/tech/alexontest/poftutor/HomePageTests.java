package tech.alexontest.poftutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleDriverTest;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.alexontest.poftutor.Constants.MAX_POSTS_PER_LISTING_PAGE;
import static tech.alexontest.poftutor.Constants.WIDGETS_PER_PAGE;

@Tag("Content")
class HomePageTests extends AbstractSingleDriverTest {
    private HomePage homePage;

    @BeforeEach
    void setupHomepage() {
        homePage = new PfHomePage(getDriver("https://alexanderontesting.com/"));
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
                .isEqualTo(WIDGETS_PER_PAGE);
    }

    @Test
    @DisplayName("No more than 5 posts are linked on the Homepage")
    void pageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(MAX_POSTS_PER_LISTING_PAGE);
    }
}
