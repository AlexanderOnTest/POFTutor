package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;
import org.openqa.selenium.WebElement;

import java.util.List;

@ImplementedBy(HomePageDesktop.class)
public interface HomePage extends Page {
    String getTitle();

    List<WebElement> getWidgets();

    List<WebElement> getArticles();
}
