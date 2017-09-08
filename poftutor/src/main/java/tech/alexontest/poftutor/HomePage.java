package tech.alexontest.poftutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends AbstractPage{
    private WebDriver driver;

    private By titleBy = By.cssSelector(".site-title");

    private By widgetsBy = By.cssSelector(".widget");

    private By articlesBy = By.cssSelector(".post-content");

    public HomePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(titleBy).getText();
    }

    public List<WebElement> getWidgets() {
        return driver.findElements(widgetsBy);
    }

    public List<WebElement> getArticles() {

        return driver.findElements(articlesBy);
    }
}
