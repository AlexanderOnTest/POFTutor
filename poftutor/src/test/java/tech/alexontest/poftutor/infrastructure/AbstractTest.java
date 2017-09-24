package tech.alexontest.poftutor.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/**
 * Abstract Test class that creates a new WebDriver for Each Test.
 * Supports JUnit 4 and JUnit 5 tests.
 */
abstract public class AbstractTest {
    private WebDriver driver;
    private WebDriverManager driverManager;

    @Before
    @BeforeEach
    public void setup() {
        System.out.println("Preparing AbstractDriverManager");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        System.out.println("Getting Driver");
        driver = driverManager.getDriver();
    }

    @After
    @AfterEach
    public void teardown() {
        System.out.println("Quiting Driver");
        driverManager.stopService();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getDriver(final String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        return driver;
    }
}
