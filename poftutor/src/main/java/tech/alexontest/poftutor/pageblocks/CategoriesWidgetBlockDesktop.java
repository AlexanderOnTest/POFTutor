package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.AbstractDefinedBlock;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriesWidgetBlockDesktop extends AbstractDefinedBlock implements CategoriesWidgetBlock {
    @FindBy(css = ".widget_categories")
    private WebElement rootElement;

    @FindBy(css = ".widget-title")
    private WebElement title;

    @FindBy(css = ".cat-item")
    private List<WebElement> categories;

    @Inject
    protected CategoriesWidgetBlockDesktop(final WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public List<String> getCategories() {
        return categories.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
