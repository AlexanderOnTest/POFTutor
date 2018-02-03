package tech.alexontest.poftutor.infrastructure.driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractDriverManager implements WebDriverManager {
    private WebDriver driver;

    private URL gridUrl;

    AbstractDriverManager() {
        try {
            gridUrl = new URL("http://SILENTHTPC:4444/wd/hub");
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This implementation returns the default grid URL initialised in the constructor.
     * @return URL of the grid
     */
    URL getGridUrl() {
        return gridUrl;
    }

    /**
     * Close the WebDriver and Browser.
     */
    @Override
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            System.out.println("WebDriver quit");
            driver = null;
        }
    }

    /**
     * Return a running WebDriver instance controlling and instance of the requested browser.
     * @return the WebDriver to control the browser.
     */
    @Override
    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }

    final void setDriver(final WebDriver driver) {
        this.driver = driver;
    }
}
