package tech.alexontest.poftutor.infrastructure;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that can use different browsers within a single execution.
 * Used for testing the Framework itself. Not suitable for injecting dependencies.
 */
public abstract class AbstractCrossBrowserTest {
    private WebDriverManager driverManager;

    /**
     * Teardown activities. Quit the driver and stop the driver service.
     */
    @AfterEach
    public void teardown() {
        System.out.println("Quitting WebDriver");
        driverManager.quitDriver();
        driverManager.stopService();
    }

    /**
     * Provision a WebDriverManager instance.
     * @return The appropriate WebDriverManager class
     */
    protected WebDriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Set driverManager.
     * @param driverManager driverManager to set.
     */
    protected void setDriverManager(final WebDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    protected WebDriver getDriver(final String url) {
        final WebDriver driver = driverManager.get();
        driver.get(url);
        return driver;
    }
}
