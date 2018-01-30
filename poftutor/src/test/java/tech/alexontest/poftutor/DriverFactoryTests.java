package tech.alexontest.poftutor;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.DriverType;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Framework")
class DriverFactoryTests extends AbstractCrossBrowserTest {

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "CHROME_LOCAL, Chrome, Chrome Browser Tests can be run  on screen locally",
            "CHROME_LOCAL_HEADLESS, Chrome, Chrome Browser Tests can be run headless locally.",
    })
    // Simplest way to pass multiple parameters, places them right above the test.
    @MethodSource("arguments")
    // Can be used to pass more complex to construct objects.
    @CsvFileSource(resources = "operaTests.csv")
    void driverFactoryWorks(final DriverType driverType, final String browserName) {
        setDriverManager(DriverManagerFactory.getManager(driverType));
        getDriverManager().startService();
        assertThat(getDriverManager().createDriver())
                .as(String.format("Checking That browser is of type %s", browserName))
                .isEqualToIgnoringCase(browserName);
        final String homePageURL = "https://www.example.com/";
        final WebDriver driver = getDriver(homePageURL);
        final String agentString = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
        final Pattern regex = driverType.getRegex();
        assertThat(agentString)
                .as("AgentString does not match pattern for %s", driverType.name())
                .containsPattern(driverType.getRegex());

        assertThat(driver.getCurrentUrl())
                .as(String.format(
                        "Check that browser '%1$s' successfully loads the homepage '%2$s'", browserName, homePageURL
                ))
                .isEqualToIgnoringCase(homePageURL);
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(DriverType.CHROME, "Chrome", "Chrome Browser Tests can be run on the grid"),
                Arguments.of(DriverType.EDGE, "MicrosoftEdge", "Edge Browser Tests can be run on the grid"),
                Arguments.of(DriverType.FIREFOX, "Firefox", "Firefox Browser Tests can be run on on the grid"),
                Arguments.of(DriverType.IE, "Internet Explorer", "IE11 Browser Tests can be run on the grid")
    );
    }
}