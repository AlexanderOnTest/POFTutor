package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import tech.alexontest.poftutor.infrastructure.pagefactory.BlockingElementLocatorFactory;

public abstract class AbstractDefinedBlock implements Block {
    AbstractDefinedBlock(final WebDriver webDriver) {
        final ElementLocatorFactory locatorFactory = new BlockingElementLocatorFactory(webDriver, this);
        PageFactory.initElements(locatorFactory, this);
    }
}
