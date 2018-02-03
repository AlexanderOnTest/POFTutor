package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

import java.util.List;

public final class PfHomePage extends AbstractPage implements HomePage {
    @FindBy(css = ".site-title")
    private WebElement pageTitle;

    @FindBy(css = ".widget")
    private List<WebElement> widgets;

    @FindBy(css = ".post-content")
    private List<WebElement> articles;

    @Inject
    public PfHomePage(final WebDriverManager webDriverManager) {
        super(webDriverManager.getDriver());
        PageFactory.initElements(webDriverManager.getDriver(), this);
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
