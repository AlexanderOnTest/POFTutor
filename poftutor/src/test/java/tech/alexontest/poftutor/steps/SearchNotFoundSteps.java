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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import tech.alexanderontest.guicefactory.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.pages.NoSearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;

public class SearchNotFoundSteps {
    private final NoSearchResultsPage noSearchResultsPage;

    private final WebDriver driver;

    private TestConfiguration testConfiguration;

    @Inject
    public SearchNotFoundSteps(final NoSearchResultsPage noSearchResultsPage,
                               final WebDriver driver,
                               final TestConfiguration testConfiguration) {
        this.noSearchResultsPage = noSearchResultsPage;
        this.driver = driver;
        this.testConfiguration = testConfiguration;
    }

    public SearchNotFoundSteps verifyURL(final String searchText) {
        assertThat(driver.getCurrentUrl())
                .as("SearchPage URL is incorrect.")
                .isEqualTo(noSearchResultsPage.getPageUrl(searchText));
        return this;
    }

    public SearchNotFoundSteps verifyPageTitle() {
        await("Unsuccessful search results failed to load.")
                .atMost(testConfiguration.getWaitTimeout().multiply(3))
                .ignoreExceptionsMatching(anyOf(
                        instanceOf(NoSuchElementException.class),
                        instanceOf(StaleElementReferenceException.class)))
                .untilAsserted(() -> assertThat(noSearchResultsPage.getPageTitle())
                .as("Page Title is not as expected.")
                .isEqualTo("Nothing Found"));
        return this;
    }

    public SearchNotFoundSteps verifyInputFieldContent(final String searchText) {
        assertThat(noSearchResultsPage.getMainSearchFieldText())
                .as("Search field does not contain the searched for term")
                .isEqualTo(searchText);
        return this;
    }
}
