package tech.alexontest.poftutor;

import org.junit.jupiter.api.Test;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.DriverType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.*;

public class DriverFactoryTests extends AbstractCrossBrowserTest{
    private final String homePageURL = "https://alexanderontesting.com/";
    private final String OS = System.getProperty("os.name");

    @Test
    public void chromeDriverFactoryWorks() {
        checkThatPageLoads(DriverType.CHROME, "Chrome");
    }

    @Test
    public void edgeDriverFactoryWorks() {
        assumeTrue(OS.equalsIgnoreCase("windows 10"));
        checkThatPageLoads(DriverType.EDGE, "MicrosoftEdge");
    }

    @Test
    public void firefoxDriverFactoryWorks() {
        checkThatPageLoads(DriverType.FIREFOX, "Firefox");
    }

    @Test
    public void ieDriverFactoryWorks() {
        assumeTrue(OS.contains("Windows"));
        checkThatPageLoads(DriverType.IE, "Internet Explorer");
    }

    private void checkThatPageLoads(final DriverType type, final String browserName) {
        setDriverManager(DriverManagerFactory.getManager(type));
        assertThat(getDriverManager().createDriver())
                .as("Checking That browser is of type %s", browserName)
        .isEqualToIgnoringCase(browserName);
        assertThat(getDriver(homePageURL).getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}
