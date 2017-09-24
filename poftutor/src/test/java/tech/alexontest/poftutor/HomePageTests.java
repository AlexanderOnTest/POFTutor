package tech.alexontest.poftutor;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTests extends AbstractTest {
    private HomePage homePage;

    @BeforeEach
    @Before
    public void setupHomepage() {
        //homePage = new PomHomePage("https://alexanderontesting.com/");                            //Classic implementation
        homePage = new PfHomePage(getDriver("https://alexanderontesting.com/"));                             //Page Factory implementation
    }

    @Test
    public void titleIsCorrect() {
        assertThat(homePage.getPageTitle())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    @Test
    public void pageContainsFiveWidgets() {
        assertThat(homePage.getWidgets())
                .size()
                .isEqualTo(5);
    }

    @Test
    public void pageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(5);
    }
}