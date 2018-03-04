package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlockDesktop;
import tech.alexontest.poftutor.pageblocks.TagCloudWidgetBlock;
import tech.alexontest.poftutor.pageblocks.TagCloudWidgetBlockDesktop;

import java.util.List;
import java.util.stream.Collectors;

public final class HomePageDesktop extends AbstractPage implements HomePage {

    @FindBy(css = ".site-title")
    @CacheLookup
    private WebElement title;

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

    private final TagCloudWidgetBlockDesktop tagCloudWidgetBlockDesktop;

    @Inject
    public HomePageDesktop(final WebDriver webDriver, final TagCloudWidgetBlockDesktop tagCloudWidgetBlockDesktop) {
        super(webDriver);
        this.tagCloudWidgetBlockDesktop = tagCloudWidgetBlockDesktop;
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
    public int getWidgetCount() {
        return widgets.size();
    }

    @Override
    public List<PostSummaryBlockDesktop> getArticles() {
        return articles.stream()
                .map(PostSummaryBlockDesktop::new)
                .collect(Collectors.toList());
    }

    @Override
    public TagCloudWidgetBlock getTagCloudWidgetBlock() {
        return tagCloudWidgetBlockDesktop;
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
