package tech.alexontest.poftutor.infrastructure.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * A custom element locator, which will allow the pagefactory finders to work from a defined rootElement.
 * This will lazily locate an element or an element list under the defined rootElement. This class is
 * designed for use with the {@link org.openqa.selenium.support.PageFactory} and understands the
 * annotations {@link org.openqa.selenium.support.FindBy} and {@link org.openqa.selenium.support.CacheLookup}.
 */
public class BlockingElementLocator implements ElementLocator {

    private final SearchContext driver;

    private final By rootElementBy;

    private final boolean shouldCache;

    private final By by;

    private WebElement cachedElement;

    private List<WebElement> cachedElementList;

    /**
     * Constructor for a locator for a block defined by a PageFactory annotation
     *
     * @param driver The first level searchContext, usually the WebDriver
     * @param rootElementField the By locator for the blocking rootElement
     * @param field The field on the Page Object that will hold the located value
     */
    BlockingElementLocator(final SearchContext driver,
                                  final Field rootElementField,
                                  final Field field) {
        this(driver, rootElementField, new Annotations(field));
    }

    /**
     * Use this constructor in order to process custom annotations.
     *
     * @param driver The first level searchContext, usually the WebDriver
     * @param rootElementField the rootElement Field
     * @param annotations AbstractAnnotations class implementation
     */
    BlockingElementLocator(final SearchContext driver,
                                  final Field rootElementField,
                                  final AbstractAnnotations annotations) {
        this.driver = driver;
        rootElementBy = new Annotations(rootElementField).buildBy();
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }

    /**
     * Find the element.
     */
    public WebElement findElement() {
        if (cachedElement != null && shouldCache) {
            return cachedElement;
        }

        final WebElement element = driver.findElement(rootElementBy).findElement(by);
        if (shouldCache) {
            cachedElement = element;
        }

        return element;
    }

    /**
     * Find the element list.
     */
    public List<WebElement> findElements() {
        if (cachedElementList != null && shouldCache) {
            return cachedElementList;
        }

        final List<WebElement> elements = driver.findElement(rootElementBy).findElements(by);
        if (shouldCache) {
            cachedElementList = elements;
        }

        return elements;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " '" + by + "'";
    }
}