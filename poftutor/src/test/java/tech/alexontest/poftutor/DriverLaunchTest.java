package tech.alexontest.poftutor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverLaunchTest {

    @Test
    public void webdriverCanBeStarted() {
        //launch a chromedriver
        System.out.println("Preparing Driver");

        //find the chromedriver and set its location in a system property
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", path);

        //the chromeoptions we can explore later, but this will maximise it on startup
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        System.out.println("Driver Started");

        //load my homepage
        driver.get("https://alexanderontesting.com/");

        //confirm the title text
        assertEquals("Alexander On Testing", driver.findElement(By.cssSelector(".site-title")).getText());

        //close the webdriver
        driver.quit();
    }

}
