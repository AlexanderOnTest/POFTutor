package tech.alexontest.poftutor.pages;

import org.openqa.selenium.WebDriver;

abstract class AbstractPage implements Page {
    private final WebDriver driver;

    AbstractPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
