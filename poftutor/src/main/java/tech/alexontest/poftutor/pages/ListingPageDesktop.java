package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractPage;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlockDesktop;

import java.util.List;
import java.util.stream.Collectors;

public class ListingPageDesktop extends AbstractPage implements ListingPage {

    @FindBy(css = ".site-title")
    @CacheLookup
    private WebElement siteTitle;

    @FindBy(css = ".widget")
    @CacheLookup
    private List<WebElement> widgets;

    @FindBy(css = ".post-content")
    @CacheLookup
    private List<WebElement> articles;

    @FindBy(css = ".site-info")
    private WebElement footerText;

    @FindBy(css = ".site-info a")
    private List<WebElement> footerLinks;

    @Inject
    ListingPageDesktop(final WebDriver webDriver) {
        super(webDriver);
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
    public String getSiteTitle() {
        return siteTitle.getText();
    }

    @Override
    public int getWidgetCount() {
        return widgets.size();
    }

    @Override
    public List<PostSummaryBlock> getArticles() {
        return articles.stream()
                .map(PostSummaryBlockDesktop::new)
                .collect(Collectors.toList());
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
