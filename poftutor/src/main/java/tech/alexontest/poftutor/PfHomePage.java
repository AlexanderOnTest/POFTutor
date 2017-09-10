package tech.alexontest.poftutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PfHomePage extends AbstractPage implements HomePage {
    @FindBy(css = ".site-title")
    private WebElement pageTitle;

    @FindBy(css = ".widget")
    private List<WebElement> widgets;

    @FindBy(css = ".post-content")
    private List<WebElement> articles;

    public PfHomePage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public List<WebElement> getWidgets() {
        return widgets;
    }

    @Override
    public List<WebElement> getArticles() {
        return articles;
    }
}
