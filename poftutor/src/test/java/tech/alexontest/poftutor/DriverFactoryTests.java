package tech.alexontest.poftutor;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.driver.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.driver.DriverType;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Framework")
class DriverFactoryTests extends AbstractCrossBrowserTest {

    @ParameterizedTest(name = "{0} successfully launches.")
    //Run all DriverTypes
    @EnumSource(DriverType.class)
    //Examples of how to filter which tests to run
    //  Debugging one that is failing.
    //@EnumSource(value = DriverType.class, names = "FIREFOX_LOCAL_HEADLESS")
    //  Exclude some values.
    //@EnumSource(value = DriverType.class, mode = EXCLUDE, names = {"OPERA_LOCAL", "FIREFOX_LOCAL"})
    //  Match a set of values e.g. run all local browsers only.
    //@EnumSource(value = DriverType.class, mode = MATCH_ALL, names = "^.*LOCAL.*$")
    void driverFactoryWorks(final DriverType driverType) {
        final String browserName = driverType.getWebdriverName();

        setDriverManager(DriverManagerFactory.getManager(driverType));
        getDriverManager().startService();

        assertThat(getDriverManager().createDriver())
                .as(String.format("Checking That browser is of type %s", browserName))
                .isEqualToIgnoringCase(browserName);

        final String homePageURL = "https://www.example.com/";
        final WebDriver driver = getDriver(homePageURL);
        final String agentString = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");

        assertThat(agentString)
                .as("AgentString does not match pattern for %s", driverType.name())
                .containsPattern(driverType.getRegex());

        assertThat(driver.getCurrentUrl())
                .as(String.format(
                        "Check that '%1$s' successfully loads the homepage '%2$s'", driverType.name(), homePageURL
                ))
                .isEqualToIgnoringCase(homePageURL);
    }
}
