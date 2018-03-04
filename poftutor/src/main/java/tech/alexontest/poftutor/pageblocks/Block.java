package tech.alexontest.poftutor.pageblocks;

import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface Block {
    WebElement getRootElement();
}
