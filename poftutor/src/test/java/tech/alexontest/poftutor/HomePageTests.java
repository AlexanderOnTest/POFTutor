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

package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;

@Tag("Content")
class HomePageTests extends AbstractSingleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @BeforeAll
    void setupHomepage() {
        homePageSteps.loadHomePage();
    }

    @Test
    @DisplayName("Reported URL is correct")
    void correctUrlIsReported() {
        homePageSteps.assertThatReportedUrlIsCorrect();
    }

    @Test
    @DisplayName("Tab page title is correct")
    void correctTitleIsDisplayedOnTheBrowserTab() {
        homePageSteps.assertThatBrowserTabPageTitleIsCorrect();
    }

    @Test
    @DisplayName("The correct title is reported")
    void titleIsCorrect() {
        homePageSteps.assertThatTitleIsCorrect();
    }

    @Test
    @DisplayName("No more than 5 posts are linked on the Homepage")
    void pageContainsUpToFiveArticles() {
        homePageSteps.assertThatPageContainsUpToFiveArticles();
    }

    @Test
    @DisplayName("Footer Text is correct.")
    void footerTextIsCorrect() {
        homePageSteps.assertThatFooterTextIsCorrect();
    }

    @Test
    @DisplayName("Footer Links are valid.")
    void footerLinksAreValid() {
        homePageSteps.assertThatFooterLinksAreNotBroken();
    }
}
