package tech.alexontest.poftutor.infrastructure.pagefactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import tech.alexontest.poftutor.pageblocks.Block;

import java.lang.reflect.Field;

public class BlockingElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext driver;

    private final Field rootElementField;

    public BlockingElementLocatorFactory(final SearchContext driver, final Block block) {
        this.driver = driver;
        try {
            this.rootElementField = block.getClass().getDeclaredField("rootElement");
        } catch (final NoSuchFieldException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ElementLocator createLocator(final Field field) {
        return (field.getName().equals("rootElement"))
                ? new DefaultElementLocator(driver, field)
                : new BlockingElementLocator(driver, rootElementField, field);
    }
}
