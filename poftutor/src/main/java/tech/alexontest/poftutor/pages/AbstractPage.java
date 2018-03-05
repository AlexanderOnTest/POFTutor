package tech.alexontest.poftutor.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract class AbstractPage implements Page {
    AbstractPage(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
