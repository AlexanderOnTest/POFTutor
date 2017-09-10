package tech.alexontest.poftutor;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface HomePage {
    String getPageTitle();

    List<WebElement> getWidgets();

    List<WebElement> getArticles();
}
