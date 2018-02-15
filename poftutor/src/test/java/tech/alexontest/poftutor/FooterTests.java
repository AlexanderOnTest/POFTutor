package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractMultipleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;

@Tag("Content")
class FooterTests extends AbstractMultipleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @BeforeEach
    void setupHomepage() {
        homePageSteps.loadHomePage();
    }

    @Test
    @DisplayName("Footer Text is correct.")
    void footerTextIsCorrect() {
        homePageSteps.assertThatFooterTextIsCorrect();
    }

    @Test
    @DisplayName("Footer Links are valid.")
    void footerLinksAreValid() {
        homePageSteps.assertThatFooterLinksAreNotBroken();
    }
}
