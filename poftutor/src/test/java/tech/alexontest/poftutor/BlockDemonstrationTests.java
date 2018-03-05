package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractMultipleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;

class BlockDemonstrationTests extends AbstractMultipleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @BeforeEach
    void setupHomepage() {
        homePageSteps.loadHomePage();
    }

    @Test
    @DisplayName("Block from a found rootElement Work Demonstration")
    void foundBlockWorks() {
        homePageSteps
                .printAllPostDetails();
    }

    @Test
    @DisplayName("Block from a defined rootElement works")
    void definedBlockWorks() {
        homePageSteps
                .assertTagCloudTitle()
                .printTags();
    }

    @Test
    @DisplayName("RootElement locator works")
    void rootElementLocatorWorks() {
        homePageSteps.verifyThatBlockRootElementWorks();

    }
}
