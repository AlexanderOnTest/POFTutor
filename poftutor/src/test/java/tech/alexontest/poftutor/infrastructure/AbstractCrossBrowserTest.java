package tech.alexontest.poftutor.infrastructure;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

/**
 * Abstract Test class that creates a new WebDriver for Each Test.
 * Supports JUnit 4 and JUnit 5 tests.
 */
abstract public class AbstractCrossBrowserTest {
    private WebDriverManager driverManager;

    @After
    @AfterEach
    public void teardown() {
        System.out.println("Quitting Driver");
        driverManager.stopService();
    }

    protected WebDriverManager getDriverManager() {
        return driverManager;
    }

    protected void setDriverManager(final WebDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    protected WebDriver getDriver() {
        return driverManager.getDriver();
    }

    protected WebDriver getDriver(final String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        return driver;
    }
}
