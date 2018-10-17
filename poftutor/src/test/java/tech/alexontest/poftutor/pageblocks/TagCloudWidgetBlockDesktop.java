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
import tech.alexanderontest.guicefactory.infrastructure.pagefactory.AbstractDefinedBlock;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TagCloudWidgetBlockDesktop extends AbstractDefinedBlock implements TagCloudWidgetBlock {

    @FindBy(className = "widget_tag_cloud")
    private WebElement rootElement;

    @FindBy(className = "widget-title")
    private WebElement title;

    @FindBy(className = "tag-cloud-link")
    private List<WebElement> tags;

    @Inject
    public TagCloudWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<Pair<String, String>> getTags() {
        return tags.stream()
                .map(we -> Pair.of(we.getText(), we.getAttribute("style")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getProminentTags() {
        return tags.stream()
                .map(this::returnTagNameIfProminent)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private String returnTagNameIfProminent(final WebElement tag) {
        final Pattern regex = Pattern.compile("(\\d+)");
        final Matcher matcher = regex.matcher(tag.getAttribute("style"));
        assertThat(matcher.find())
                .as("The Tag did not include a font size style as expected.")
                .isTrue();
        // return the Tag name only if the font size > 15
        return Integer.parseInt(matcher.group(1)) > 15 ? tag.getText() : "";
    }
}
