package tech.alexanderontest.guicefactory.infrastructure.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Abstract super class to aid in the construction of blocks defined by a previously instantiated rootElement.
 * Be cautious using these blocks for testing AJAX applications, the rootElement is prone to
 * StaleElementReferenceExceptions if a DOM element above it in the tree is updated.
 */
public abstract class AbstractFoundBlock implements Block {

    private final WebElement rootElement;

    protected AbstractFoundBlock(final WebElement rootElement) {
        final ElementLocatorFactory locatorFactory = new BlockingElementLocatorFactory(rootElement);
        PageFactory.initElements(locatorFactory, this);
        this.rootElement = rootElement;
    }

    @Override
    public WebElement getRootElement() {
        return rootElement;
    }
}
