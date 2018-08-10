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
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import tech.alexanderontest.guicefactory.infrastructure.pagefactory.AbstractPage;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlockDesktop;

import java.util.List;
import java.util.stream.Collectors;

public class ListingPageDesktop extends AbstractPage implements ListingPage {

    @FindBy(css = ".site-title")
    @CacheLookup
    private WebElement siteTitle;

    @FindBy(css = ".widget")
    @CacheLookup
    private List<WebElement> widgets;

    @FindBy(css = ".post-content")
    @CacheLookup
    private List<WebElement> articles;

    @FindBy(css = ".site-info")
    private WebElement footerText;

    @FindBy(css = ".site-info a")
    private List<WebElement> footerLinks;

    @Inject
    ListingPageDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getBrowserTabPageTitle() {
        return "Alexander on Testing – Adventures in Software Testing";
    }

    @Override
    public String getURL() {
        return "https://alexanderontesting.com/";
    }

    @Override
    public String getSiteTitle() {
        return siteTitle.getText();
    }

    @Override
    public int getWidgetCount() {
        return widgets.size();
    }

    @Override
    public List<PostSummaryBlock> getArticles() {
        return articles.stream()
                .map(PostSummaryBlockDesktop::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getFooterText() {
        return footerText.getText();
    }

    @Override
    public List<String> getFooterLinks() {
        return footerLinks.stream()
                .map(we -> we.getAttribute("href"))
                .collect(Collectors.toList());
    }
}
