package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractDriverManager implements WebDriverManager {
    protected WebDriver driver;
    private URL gridUrl;

    AbstractDriverManager() {
        try {
            gridUrl = new URL("http://192.168.0.17:4445/wd/hub");
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }

    URL getGridUrl() {
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
    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}