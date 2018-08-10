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

package tech.alexontest.poftutor.pageblocks;

import com.google.inject.Inject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import tech.alexanderontest.guicefactory.infrastructure.configuration.TestConfiguration;
import tech.alexanderontest.guicefactory.infrastructure.pagefactory.AbstractDefinedBlock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class SearchWidgetBlockDesktop extends AbstractDefinedBlock implements SearchWidgetBlock {
    @FindBy(css = ".widget_search")
    private WebElement rootElement;

    @FindBy(css = ".search-field")
    private WebElement searchInput;

    @FindBy(css = ".search-submit")
    private WebElement submitButton;

    private final TestConfiguration testConfiguration;

    @Inject
    protected SearchWidgetBlockDesktop(final WebDriver driver,
                                       final TestConfiguration testConfiguration) {
        super(driver);
        this.testConfiguration = testConfiguration;
    }

    @Override
    public SearchWidgetBlock enterSearchText(final String searchText) {
        new FluentWait<>(searchInput)
                .ignoring(StaleElementReferenceException.class)
                .until(WebElement::isDisplayed);
        searchInput.clear();
        searchInput.sendKeys(searchText);
        await("Failed to wait for search text to appear")
                .atMost(testConfiguration.getWaitTimeout())
                .untilAsserted(() -> assertThat(getSearchBarText())
                                .as("Search Bar Text was not as expected")
                                .isEqualTo(searchText));
        return this;
    }

    @Override
    public String getSearchBarText() {
        return searchInput.getAttribute("value");
    }

    @Override
    public void searchByEnter(final String searchText) {
        searchInput.clear();
        searchInput.sendKeys(searchText);
        searchInput.submit();
    }

    @Override
    public void submitSearchByButton() {
        // this fails on Safari due to a bug: Discussion: https://github.com/lionheart/openradar-mirror/issues/19107
        submitButton.click();
    }
}
