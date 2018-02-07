package tech.alexontest.poftutor.infrastructure.driver;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

public interface WebDriverManager extends Provider<WebDriver> {
    String createDriver();

    void startService();

    void stopService();

    void quitDriver();

    WebDriver get();
}
