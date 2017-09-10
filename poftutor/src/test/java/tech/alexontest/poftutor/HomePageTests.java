package tech.alexontest.poftutor;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTests extends AbstractTest {
    private HomePage homePage;

    @BeforeEach
    @Before
    public void setupHomepage() {
        final WebDriver driver = getDriver();
        driver.get("https://alexanderontesting.com/");
        //homePage = new PomHomePage(driver);                            //Classic implementation
        homePage = new PfHomePage(driver);                             //Page Factory implementation
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