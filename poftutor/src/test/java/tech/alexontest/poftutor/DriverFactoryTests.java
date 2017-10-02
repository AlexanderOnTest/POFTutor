package tech.alexontest.poftutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.DriverType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("Framework")
class DriverFactoryTests extends AbstractCrossBrowserTest{
    private final String homePageURL = "https://www.bbc.co.uk/";
    private final String OS = System.getProperty("os.name");

    @Test
    @DisplayName("Chrome Browser Tests can be run on Windows")
    void chromeDriverFactoryWorks() {
        checkThatPageLoads(DriverType.CHROME, "Chrome");
    }

    @Test
    @DisplayName("Edge Browser Tests can be run on Windows 10")
    void edgeDriverFactoryWorks() {
        assumeTrue(OS.equalsIgnoreCase("windows 10"), "Edge only runs on Windows 10.");
        checkThatPageLoads(DriverType.EDGE, "MicrosoftEdge");
    }

    @Test
    @DisplayName("Firefox Browser Tests can be run on Windows")
    void firefoxDriverFactoryWorks() {
        checkThatPageLoads(DriverType.FIREFOX, "Firefox");
    }

    @Test
    @DisplayName("Internet Explorer Browser Tests can be run on Windows")
    void ieDriverFactoryWorks() {
        assumeTrue(OS.contains("Windows"));
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
