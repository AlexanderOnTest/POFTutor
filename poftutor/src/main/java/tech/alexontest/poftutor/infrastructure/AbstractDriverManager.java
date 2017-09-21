package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverManager {
    protected WebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    public abstract String createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}