package tech.alexontest.poftutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Content")
class HomePageTests extends AbstractTest {
    private HomePage homePage;

    @BeforeEach
    void setupHomepage() {
        //homePage = new PomHomePage("https://alexanderontesting.com/");                            //Classic implementation
        homePage = new PfHomePage(getDriver("https://alexanderontesting.com/"));                             //Page Factory implementation
    }

    @Test
    void titleIsCorrect() {
        assertThat(homePage.getPageTitle())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    @Test
    void pageContainsFiveWidgets() {
        assertThat(homePage.getWidgets())
                .size()
                .isEqualTo(5);
    }

    @Test
    void pageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(5);
    }
}