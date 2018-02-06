package tech.alexontest.poftutor.infrastructure.driver;

import org.openqa.selenium.WebDriver;

public interface WebDriverManager {
    String createDriver();

    void startService();

    void stopService();

    void quitDriver();

    WebDriver getDriver();
}
