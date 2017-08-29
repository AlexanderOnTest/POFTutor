package tech.alexontest.poftutor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverLaunchTest {
    private WebDriver driver;

    @Before
    @BeforeEach
    public void setup() {
        //launch a chromedriver
        System.out.println("Preparing Driver");

        //find the chromedriver and set its location in a system property
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", path);

        //the chromeoptions we can explore later, but this will maximise it on startup
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        System.out.println("Driver Started");
    }

    @Test
    public void webdriverCanBeStarted() {
        //load my homepage
        driver.get("https://alexanderontesting.com/");

        //confirm the title text is correct
        assertThat(driver.findElement(By.cssSelector(".site-title")).getText())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    @After
    @AfterEach
    public void teardown() {
        //close the webdriver
        driver.quit();
    }
}