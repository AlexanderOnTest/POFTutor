package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverManager implements WebDriverManager {
    protected WebDriver driver;

    @Override
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
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