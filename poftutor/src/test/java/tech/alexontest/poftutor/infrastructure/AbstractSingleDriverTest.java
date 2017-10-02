package tech.alexontest.poftutor.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

/**
 * AbstractTest class that reuses the same WebDriver for all tests.
 * Only supports JUnit 5 tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract public class AbstractSingleDriverTest {
    private WebDriverManager driverManager;

    @BeforeAll
    void setup() {
        System.out.println("Preparing DriverManager");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        System.out.println("Getting Driver");
    }

    @AfterAll
    void teardown() {
        driverManager.quitDriver();
        driverManager.stopService();
    }

    protected WebDriver getDriver(final String url) {
        WebDriver driver = driverManager.getDriver();
        driver.get(url);
        return driver;
    }
}
