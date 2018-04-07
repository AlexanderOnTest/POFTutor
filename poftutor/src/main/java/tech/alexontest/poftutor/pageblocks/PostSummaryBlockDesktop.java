package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractFoundBlock;

public class PostSummaryBlockDesktop extends AbstractFoundBlock implements PostSummaryBlock {

    @FindBy(css = ".entry-title")
    @CacheLookup
    private WebElement postTitle;

    @FindBy(css = ".entry-date .entry-date")
    @CacheLookup
    private WebElement postDate;

    @FindBy(css = ".author")
    @CacheLookup
    private WebElement authorName;

    @FindBy(css = ".entry-summary p")
    @CacheLookup
    private WebElement postSummary;

    @FindBy(css = ".read-more .screen-reader-text")
    @CacheLookup
    private WebElement readMore;

    public PostSummaryBlockDesktop(final WebElement rootElement) {
        super(rootElement);
    }

    @Override
    public String getPostTitle() {
        return postTitle.getText();
    }

    @Override
    public String getAuthorName() {
        return authorName.getText();
    }

    @Override
    public String getPostDate() {
        return postDate.getText();
    }

    @Override
    public String getPostSummary() {
        return postSummary.getText();
    }

    @Override
    public String getReadMore() {
        return readMore.getText();
    }
}
