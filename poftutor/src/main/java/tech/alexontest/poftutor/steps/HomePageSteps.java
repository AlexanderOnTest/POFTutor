package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;
import tech.alexontest.poftutor.pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.alexontest.poftutor.Constants.MAX_POSTS_PER_LISTING_PAGE;
import static tech.alexontest.poftutor.Constants.WIDGETS_PER_PAGE;

public class HomePageSteps {
    private final HomePage homePage;

    private final TestConfiguration testConfiguration;

    private final WebDriver webDriver;

    @Inject
    public HomePageSteps(final HomePage homePage,
                         final TestConfiguration testConfiguration,
                         final WebDriverManager driverManager) {
        this.homePage = homePage;
        this.testConfiguration = testConfiguration;
        webDriver = driverManager.getDriver();
    }

    public void loadHomePage() {
        webDriver.navigate().to(testConfiguration.getHomePageUrl());
    }

    public HomePageSteps attemptToLoadHomePageFromUrl(final String url) {
        webDriver.navigate().to(url);
        return this;
    }

    public void assertThatReportedUrlIsCorrect() {
        assertThat(webDriver.getCurrentUrl())
                .as("Reported URL is not as expected")
                .isEqualTo(homePage.getURL());
    }

    public void assertThatBrowserTabPageTitleIsCorrect() {
        assertThat(webDriver.getTitle())
                .as("Reported browser tab page title is not as expected")
                .isEqualTo(homePage.getBrowserTabPageTitle());
    }

    public void assertThatTitleIsCorrect() {
        assertThat(homePage.getTitle())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    public void assertThatPageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(MAX_POSTS_PER_LISTING_PAGE);
    }

    public void assertThatPageContainsFiveWidgets() {
        assertThat(homePage.getWidgets())
                .size()
                .isEqualTo(WIDGETS_PER_PAGE);
    }
}
