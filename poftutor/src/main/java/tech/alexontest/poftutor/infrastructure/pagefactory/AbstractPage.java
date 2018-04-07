package tech.alexontest.poftutor.infrastructure.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("squid:S1610")
public abstract class AbstractPage implements Page {
    protected AbstractPage(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
