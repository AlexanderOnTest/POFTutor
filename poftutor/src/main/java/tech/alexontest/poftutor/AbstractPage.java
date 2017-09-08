package tech.alexontest.poftutor;

import org.openqa.selenium.WebDriver;

abstract class AbstractPage implements Page{
    private WebDriver driver;

    protected AbstractPage(final WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }
}
