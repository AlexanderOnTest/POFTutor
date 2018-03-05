package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import tech.alexontest.poftutor.infrastructure.pagefactory.BlockingElementLocatorFactory;

public abstract class AbstractFoundBlock implements Block {

    private final WebElement rootElement;

    AbstractFoundBlock(final WebElement rootElement) {
        final ElementLocatorFactory locatorFactory = new BlockingElementLocatorFactory(rootElement);
        PageFactory.initElements(locatorFactory, this);
        this.rootElement = rootElement;
    }

    @Override
    public WebElement getRootElement() {
        return rootElement;
    }
}
