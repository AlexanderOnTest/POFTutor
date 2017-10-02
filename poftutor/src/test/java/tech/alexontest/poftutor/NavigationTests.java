package tech.alexontest.poftutor;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleDriverTest;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Navigation")
class NavigationTests extends AbstractSingleDriverTest {
    private final String homePageURL = "https://alexanderontesting.com/";

    @Test
    @DisplayName("Homepage URL displays correctly")
    void urlIsCorrect() {
        assertThat(getDriver(homePageURL).getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Disabled("30/09/2017 Broken - re-enable after fixing")
    @Test
    @DisplayName("http is directed to https")
    void httpRewriteIsWorking() {
        assertThat(getDriver("http://alexanderontesting.com/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Disabled("Until I have time to set up the forwarding")
    @Test
    @DisplayName("http://alexontest.tech is redirected here")
    void alexOnTestHttpRewriteIsWorking() {
        assertThat(getDriver("http://alexontest.tech/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Disabled("Until I have time to set up the forwarding")
    @Test
    @DisplayName("https://alexontest.tech is redirected here")
    void alexOnTestHttpsRewriteIsWorking() {
        assertThat(getDriver("https://alexontest.tech/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}