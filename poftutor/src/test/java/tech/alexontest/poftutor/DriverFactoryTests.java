package tech.alexontest.poftutor;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.DriverType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.*;

public class DriverFactoryTests extends AbstractCrossBrowserTest{
    private final String homePageURL = "https://www.bbc.co.uk/";
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
        WebDriver driver = getDriverManager().getDriver();
        driver.get(homePageURL);
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }
}
