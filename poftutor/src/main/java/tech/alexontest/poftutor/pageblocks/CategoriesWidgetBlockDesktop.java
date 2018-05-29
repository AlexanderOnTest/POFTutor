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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractDefinedBlock;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriesWidgetBlockDesktop extends AbstractDefinedBlock implements CategoriesWidgetBlock {
    @FindBy(css = ".widget_categories")
    private WebElement rootElement;

    @FindBy(css = ".widget-title")
    private WebElement title;

    @FindBy(css = ".cat-item")
    private List<WebElement> categories;

    @Inject
    protected CategoriesWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<String> getCategories() {
        return categories.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
