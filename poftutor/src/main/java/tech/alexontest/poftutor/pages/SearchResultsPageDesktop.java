package tech.alexontest.poftutor.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPageDesktop extends ListingPageDesktop implements SearchResultsPage {
    @FindBy(css = ".search-page-title")
    private WebElement searchPageTitle;

    @Inject
    SearchResultsPageDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getPageUrl(final String searchText) {
        return String.format("https://alexanderontesting.com/?s=%s",
                searchText.replaceAll(" ", "+"));
    }

    @Override
    public String getPageTitle() {
        return searchPageTitle.getText();
    }
}
