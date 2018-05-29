package tech.alexontest.poftutor.pageblocks;

import com.google.inject.Inject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractDefinedBlock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class SearchWidgetBlockDesktop extends AbstractDefinedBlock implements SearchWidgetBlock {
    @FindBy(css = ".widget_search")
    private WebElement rootElement;

    @FindBy(css = ".search-field")
    private WebElement searchInput;

    @FindBy(css = ".search-submit")
    private WebElement submitButton;

    private final TestConfiguration testConfiguration;

    @Inject
    protected SearchWidgetBlockDesktop(final WebDriver driver,
                                       final TestConfiguration testConfiguration) {
        super(driver);
        this.testConfiguration = testConfiguration;
    }

    @Override
    public SearchWidgetBlock enterSearchText(final String searchText) {
        new FluentWait<>(searchInput)
                .ignoring(StaleElementReferenceException.class)
                .until(WebElement::isDisplayed);
        searchInput.clear();
        searchInput.sendKeys(searchText);
        await("Failed to wait for search text to appear")
                .atMost(testConfiguration.getWaitTimeout())
                .untilAsserted(() -> assertThat(getSearchBarText())
                                .as("Search Bar Text was not as expected")
                                .isEqualTo(searchText));
        return this;
    }

    @Override
    public String getSearchBarText() {
        return searchInput.getAttribute("value");
    }

    @Override
    public void searchByEnter(final String searchText) {
        searchInput.clear();
        searchInput.sendKeys(searchText);
        searchInput.submit();
    }

    @Override
    public void submitSearchByButton() {
        // this fails on Safari due to a bug: Discussion: https://github.com/lionheart/openradar-mirror/issues/19107
        submitButton.click();
    }
}
