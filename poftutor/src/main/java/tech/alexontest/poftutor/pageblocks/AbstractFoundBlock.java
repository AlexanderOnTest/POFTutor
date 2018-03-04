package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public abstract class AbstractFoundBlock implements Block {

    private final WebElement rootElement;

    AbstractFoundBlock(final WebElement rootElement) {
        final ElementLocatorFactory locatorFactory = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(locatorFactory, this);
        this.rootElement = rootElement;
    }

    @Override
    public WebElement getRootElement() {
        return rootElement;
    }
}
