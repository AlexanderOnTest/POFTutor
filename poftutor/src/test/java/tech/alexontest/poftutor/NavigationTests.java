package tech.alexontest.poftutor;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationTests extends AbstractTest {
    private WebDriver driver;
    private final String homePageURL = "https://alexanderontesting.com/";

    public void setupHomepage(final String URL) {
        driver = getDriver();
        driver.get(URL);
    }

    @Test
    public void urlIsCorrect() {
        setupHomepage(homePageURL);
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Test
    public void httpRewriteIsWorking() {
        setupHomepage("http://alexanderontesting.com/");
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Ignore //not yet implemented
    @Test
    public void alexOnTestHttpRewriteIsWorking() {
        setupHomepage("http://alexontest.tech/");
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Ignore //not yet implemented
    @Test
    public void alexOnTestHttpsRewriteIsWorking() {
        setupHomepage("https://alexontest.tech/");
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}