package tech.alexontest.poftutor.infrastructure;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

/**
 * Abstract Test class that creates a new WebDriver for Each Test.
 * Supports JUnit 4 and JUnit 5 tests.
 */
abstract public class AbstractCrossBrowserTest {
    private WebDriver driver;
    private AbstractDriverManager driverManager;

    @After
    @AfterEach
    public void teardown() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
        driverManager.stopService();
    }

    protected AbstractDriverManager getDriverManager() {
        return driverManager;
    }

    protected void setDriverManager(final AbstractDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public void setDriver(final WebDriver driver) {
        this.driver = driver;
    }
}
