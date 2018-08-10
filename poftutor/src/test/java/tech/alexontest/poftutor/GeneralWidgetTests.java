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
import tech.alexanderontest.guicefactory.infrastructure.AbstractSingleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;
import tech.alexontest.poftutor.steps.WidgetSteps;

@Tag("Content")
@Tag("Widgets")
class GeneralWidgetTests extends AbstractSingleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @Inject
    private WidgetSteps widgetSteps;

    @BeforeAll
    void setupHomepage() {
        homePageSteps.loadHomePage();
    }

    @Test
    @DisplayName("At Least 5 Widgets appear on the Homepage")
    void pageContainsAtLeastFiveWidgets() {
        homePageSteps.assertThatPageContainsAtLeastFiveWidgets();
    }

    @Test
    @DisplayName("Tag cloud is present and correct")
    void tagCloudIsCorrect() {
        widgetSteps.assertTagCloudTitle();
    }

    @Test
    @DisplayName("The expected Tags are the largest")
    void mostImportantTagsAreProminent() {
        widgetSteps.assertMostProminentTags();
    }

    @Test
    @DisplayName("Search widget is present and text can be entered")
    void searchWidgetIsPresent() {
        widgetSteps.verifyYouCanEnterSearchText("This is a test");
    }

    @Test
    @DisplayName("The 'Categories' widget is displayed correctly")
    void categoriesWidgetIsCorrect() {
        widgetSteps.verifyCategoriesWidgetLayout();
    }

    @Test
    @DisplayName("The 'archives' widget is displayed correctly")
    void archivesWidgetIsCorrect() {
        widgetSteps.verifyArchivesWidgetLayout();
    }

    @Test
    @DisplayName("The 'archives' widget links are all valid")
    void archivesWidgetLinksAreNotBroken() {
        widgetSteps.verifyArchivesLinksAreValid();
    }

    @Test
    @DisplayName("The 'meta' widget is displayed correctly")
    void metaWidgetIsCorrect() {
        widgetSteps.verifyMetaWidgetLayout();
    }
}
