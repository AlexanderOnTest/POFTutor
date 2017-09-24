package tech.alexontest.poftutor;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractSingleDriverTest;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationTests extends AbstractSingleDriverTest {
    private final String homePageURL = "https://alexanderontesting.com/";

    @Test
    public void urlIsCorrect() {
        assertThat(getDriver(homePageURL).getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Test
    public void httpRewriteIsWorking() {
        assertThat(getDriver("http://alexanderontesting.com/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Disabled
    @Ignore //not yet implemented
    @Test
    public void alexOnTestHttpRewriteIsWorking() {
        assertThat(getDriver("http://alexontest.tech/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Disabled
    @Ignore //not yet implemented
    @Test
    public void alexOnTestHttpsRewriteIsWorking() {
        assertThat(getDriver("https://alexontest.tech/").getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}