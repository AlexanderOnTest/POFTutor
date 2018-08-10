package tech.alexanderontest.guicefactory.infrastructure.pagefactory;

import org.openqa.selenium.WebElement;

/**
 * Interface for the support of PageBlock classes.
 * Implementations should use the PageFactory to proxy fields relative to the WebElement rootElement field.
 */
@FunctionalInterface
public interface Block {
    WebElement getRootElement();
}
