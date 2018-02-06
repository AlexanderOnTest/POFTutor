package tech.alexontest.poftutor.pages;

import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

abstract class AbstractPage implements Page {
    private final WebDriver driver;

    AbstractPage(final WebDriverManager webDriverManager) {
        this.driver = webDriverManager.getDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
