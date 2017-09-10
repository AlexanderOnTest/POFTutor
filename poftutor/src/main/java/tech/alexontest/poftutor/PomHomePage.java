package tech.alexontest.poftutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PomHomePage extends AbstractPage implements HomePage {
    private WebDriver driver;

    private By titleBy = By.cssSelector(".site-title");

    private By widgetsBy = By.cssSelector(".widget");

    private By articlesBy = By.cssSelector(".post-content");

    public PomHomePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public String getPageTitle() {
        return driver.findElement(titleBy).getText();
    }

    @Override
    public List<WebElement> getWidgets() {
        return driver.findElements(widgetsBy);
    }

    @Override
    public List<WebElement> getArticles() {
        return driver.findElements(articlesBy);
    }
}
