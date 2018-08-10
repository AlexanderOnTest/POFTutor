package tech.alexanderontest.guicefactory.infrastructure.driver;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

/**
 * Interface for a generic WebDriverManager.
 */
public interface WebDriverManager extends Provider<WebDriver> {
    /**
     * Create a new WebDriver instance according to the configuration options.
     * @return the BrowserName that was requested in the options.
     */
    String createDriver();

    /**
     * Start the local WebDriver service if required.
     */
    void startService();

    /**
     * Stop the local WebDriver service if running.
     */
    void stopService();

    /**
     * Close the current WebDriver and Browser.
     */
    void quitDriver();

    /**
     * Return a running WebDriver instance controlling and instance of the requested browser.
     * @return the WebDriver to control the browser.
     */
    WebDriver get();
}
