package tech.alexontest.poftutor.pageblocks;

import com.google.inject.Inject;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TagCloudWidgetBlockDesktop extends AbstractDefinedBlock implements TagCloudWidgetBlock {

    @FindBy(className = "widget_tag_cloud")
    private WebElement rootElement;

    @FindBy(className = "widget-title")
    private WebElement title;

    @FindBy(className = "tag-cloud-link")
    private List<WebElement> tags;

    @Inject
    public TagCloudWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public WebElement getRootElement() {
        return rootElement;
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<Pair<String, String>> getTags() {
        return tags.stream()
                .map(we -> Pair.of(we.getText(), we.getAttribute("style")))
                .collect(Collectors.toList());
    }
}
