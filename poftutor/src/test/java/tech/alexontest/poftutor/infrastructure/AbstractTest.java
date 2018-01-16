package tech.alexontest.poftutor.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

/**
 * My default AbstractTest class that creates a new WebDriver of the same type for Each Test.
 * Supports only JUnit 5 tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {

    private WebDriver driver;

    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        System.out.println("Preparing DriverManager");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeEach
    void setup() {
        System.out.println("Getting Driver");
        driver = driverManager.getDriver();
    }

    @AfterEach
    void teardown() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
    }

    @AfterAll
    void finalise() {
        driverManager.stopService();
    }

    protected WebDriver getDriver(final String url) {
        driver.get(url);
        return driver;
    }

    protected WebDriverManager getDriverManager() {
        return driverManager;
    }
}
