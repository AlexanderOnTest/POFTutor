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

package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;

import java.util.Collections;
import java.util.List;

public class NoSearchResultsPageDesktop extends SearchResultsPageDesktop implements NoSearchResultsPage {
    @FindBy(css = ".page-title")
    private WebElement pageTitle;

    @FindBy(css = "input")
    private WebElement mainPageSearchField;

    @Inject
    NoSearchResultsPageDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public String getMainSearchFieldText() {
        return mainPageSearchField.getAttribute("value");
    }

    @Override
    public List<PostSummaryBlock> getArticles() {
        return Collections.emptyList();
    }
}
