package tech.alexontest.poftutor.pageblocks;

import com.google.inject.Inject;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractDefinedBlock;

import java.util.List;
import java.util.stream.Collectors;

public class ArchivesWidgetBlockDesktop extends AbstractDefinedBlock implements ArchivesWidgetBlock {
    @FindBy(css = ".widget_archive")
    private WebElement rootElement;

    @FindBy(css = ".widget-title")
    private WebElement title;

    @FindBy(tagName = "a")
    private List<WebElement> months;

    @Inject
    protected ArchivesWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<Pair<String, String>> getMonths() {
        return months.stream()
                .map(e -> Pair.of(e.getText(), e.getAttribute("href")))
                .collect(Collectors.toList());
    }
}
