package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class HomePageDesktop extends AbstractPage implements HomePage {
    @FindBy(css = ".site-title")
    private WebElement title;

    @FindBy(css = ".widget")
    private List<WebElement> widgets;

    @FindBy(css = ".post-content")
    private List<WebElement> articles;

    @FindBy(css = ".site-info")
    private WebElement footerText;

    @FindBy(css = ".site-info a")
    private List<WebElement> footerLinks;

    @Inject
    public HomePageDesktop(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public String getBrowserTabPageTitle() {
        return "Alexander on Testing – Adventures in Software Testing";
    }

    @Override
    public String getURL() {
        return "https://alexanderontesting.com/";
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<WebElement> getWidgets() {
        return widgets;
    }

    @Override
    public List<WebElement> getArticles() {
        return articles;
    }

    @Override
    public String getFooterText() {
        return footerText.getText();
    }

    @Override
    public List<String> getFooterLinks() {
        return footerLinks.stream()
                .map(we -> we.getAttribute("href"))
                .collect(Collectors.toList());
    }
}
