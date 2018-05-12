package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;

import java.util.Collections;
import java.util.List;

public class NoSearchResultsPageDesktop extends SearchResultsPageDesktop implements NoSearchResultsPage {
    @FindBy(css = ".page-title")
    private WebElement pageTitle;

    @FindBy(css = "input")
    private WebElement mainPageSearchField;

    @Inject
    NoSearchResultsPageDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public String getMainSearchFieldText() {
        return mainPageSearchField.getAttribute("value");
    }

    @Override
    public List<PostSummaryBlock> getArticles() {
        return Collections.emptyList();
    }
}
