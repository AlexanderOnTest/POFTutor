package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleWebDriverTest;
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
    @DisplayName("5 Widgets appear on the Homepage")
    void pageContainsFiveWidgets() {
        homePageSteps.assertThatPageContainsFiveWidgets();
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
