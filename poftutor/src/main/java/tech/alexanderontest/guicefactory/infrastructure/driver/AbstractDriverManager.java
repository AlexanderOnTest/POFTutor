package tech.alexanderontest.guicefactory.infrastructure.driver;

import org.openqa.selenium.WebDriver;

import java.net.URL;

public abstract class AbstractDriverManager implements WebDriverManager {
    private WebDriver driver;

    private URL gridUrl;

    AbstractDriverManager(final URL gridURL) {
        this.gridUrl = gridURL;
    }

    /**
     * This implementation returns the default grid URL initialised in the constructor.
     * @return URL of the grid
     */
    protected URL getGridUrl() {
        return gridUrl;
    }

    @Override
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            System.out.println("WebDriver quit");
            driver = null;
        }
    }

    @Override
    public WebDriver get() {
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
