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
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractDefinedBlock;

import java.util.List;
import java.util.stream.Collectors;

public class MetaWidgetBlockDesktop extends AbstractDefinedBlock implements MetaWidgetBlock {
    @FindBy(css = ".widget_meta")
    private WebElement rootElement;

    @FindBy(css = ".widget-title")
    private WebElement title;

    @FindBy(tagName = "a")
    private List<WebElement> links;

    @Inject
    protected MetaWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<Pair<String, String>> getLinks() {
        return links.stream()
                .map(e -> Pair.of(e.getText(), e.getAttribute("href")))
                .collect(Collectors.toList());
    }
}
