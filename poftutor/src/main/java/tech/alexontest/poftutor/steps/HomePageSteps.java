package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.pages.HomePage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.alexontest.poftutor.Constants.MAX_POSTS_PER_LISTING_PAGE;
import static tech.alexontest.poftutor.Constants.WIDGETS_PER_PAGE;

public class HomePageSteps {
    private final HomePage homePage;

    private final TestConfiguration testConfiguration;

    private final WebDriver webDriver;

    @Inject
    public HomePageSteps(final TestConfiguration testConfiguration,
                         final WebDriver webDriver,
                         final HomePage homePage) {
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
        assertThat(homePage.getTitle())
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
                .isEqualTo("© 2018 | Proudly Powered by WordPress | Theme: Nisarg");
    }

    public void assertThatFooterLinksAreNotBroken() {
        homePage.getFooterLinks()
                .forEach(this::checkUrlExists);
    }

    /**
     * Crude check that the url leads to a valid page.
     * @param url the url to check
     */
    private void checkUrlExists(final String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            assertThat(conn.getResponseCode())
                    .isEqualTo(HttpURLConnection.HTTP_OK);
        } catch (final IOException e) {
            throw new AssertionError(String.format("Link '%s' does not return a valid response", url));
        }
    }

    public HomePageSteps assertTagCloudTitle() {
        assertThat(homePage.getTagCloudWidgetBlock().getTitle())
                .as("Tag Could Title is correct")
                .isEqualToIgnoringCase("Tags");
        return this;
    }
}
