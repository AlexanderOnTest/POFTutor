package tech.alexontest.poftutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.DriverType;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Framework")
class DriverFactoryTests extends AbstractCrossBrowserTest{
    private final String homePageURL = "https://alexanderontesting.com/";

    @Test
    @DisplayName("Chrome Browser Tests can be run on the grid on Windows 10")
    void chromeDriverFactoryWorksGrid() {
        checkThatPageLoads(DriverType.CHROME, "Chrome");
    }

    @Test
    @DisplayName("Chrome Browser Tests can be run locally on screen")
    void chromeDriverFactoryWorksLocal() {
        checkThatPageLoads(DriverType.CHROME_LOCAL, "Chrome");
    }

    @Test
    @DisplayName("Chrome Browser Tests can be run locally headless")
    void chromeDriverFactoryWorksHeadless() {
        checkThatPageLoads(DriverType.CHROME_LOCAL_HEADLESS, "Chrome");
    }

    @Test
    @DisplayName("Edge Browser Tests can be run on the grid on Windows 10")
    void edgeDriverFactoryWorks() {
        checkThatPageLoads(DriverType.EDGE, "MicrosoftEdge");
    }

    @Test
    @DisplayName("Firefox Browser Tests can be run on on the grid")
    void firefoxDriverFactoryWorks() {
        checkThatPageLoads(DriverType.FIREFOX, "Firefox");
    }

    @Test
    @DisplayName("Internet Explorer Browser Tests can be run on on the grid")
    void ieDriverFactoryWorks() {
        checkThatPageLoads(DriverType.IE, "Internet Explorer");
    }

    private void checkThatPageLoads(final DriverType type, final String browserName) {
        setDriverManager(DriverManagerFactory.getManager(type));
        getDriverManager().startService();
        assertThat(getDriverManager().createDriver())
                .as("Checking That browser is of type %s", browserName)
                .isEqualToIgnoringCase(browserName);
        assertThat(getDriver(homePageURL).getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}
