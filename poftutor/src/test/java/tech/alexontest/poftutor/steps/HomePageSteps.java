/*
 * Copyright (c) 2018. Alexander Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.alexontest.poftutor.steps;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tech.alexanderontest.guicefactory.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.HttpTools;
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
                .isEqualToIgnoringCase(homePage.getBrowserTabPageTitle());
    }

    public void assertThatTitleIsCorrect() {
        assertThat(homePage.getSiteTitle())
                .isEqualToIgnoringCase("Alexander on Test");
    }

    public void assertThatPageContainsUpToFiveArticles() {
        assertThat(homePage.getArticles())
                .size()
                .isLessThanOrEqualTo(MAX_POSTS_PER_LISTING_PAGE);
    }

    public void assertThatPageContainsAtLeastFiveWidgets() {
        assertThat(homePage.getWidgetCount())
                .isGreaterThanOrEqualTo(WIDGETS_PER_PAGE);
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
