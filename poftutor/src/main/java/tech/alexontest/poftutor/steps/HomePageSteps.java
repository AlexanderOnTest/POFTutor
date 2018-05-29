package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.HttpTools;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.pages.ListingPage;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.alexontest.poftutor.Constants.MAX_POSTS_PER_LISTING_PAGE;
import static tech.alexontest.poftutor.Constants.WIDGETS_PER_PAGE;

public class HomePageSteps {
    private final ListingPage homePage;

    private final TestConfiguration testConfiguration;

    private final WebDriver webDriver;

    @Inject
    public HomePageSteps(final TestConfiguration testConfiguration,
                         final WebDriver webDriver,
                         final ListingPage homePage) {
        this.testConfiguration = testConfiguration;
        this.webDriver = webDriver;
        this.homePage = homePage;
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
        assertThat(homePage.getSiteTitle())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    public void assertThatPageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(MAX_POSTS_PER_LISTING_PAGE);
    }

    public void assertThatPageContainsFiveWidgets() {
        assertThat(homePage.getWidgetCount())
                .isEqualTo(WIDGETS_PER_PAGE);
    }

    public void assertThatFooterTextIsCorrect() {
        assertThat(homePage.getFooterText())
                .as("FooterText is not as expected.")
                .isEqualToIgnoringWhitespace("© 2018 | Proudly Powered by WordPress | Theme: Nisarg");
    }

    public void assertThatFooterLinksAreNotBroken() {
        homePage.getFooterLinks()
                .forEach(HttpTools::assertLinkIsNotBroken);
    }
}
