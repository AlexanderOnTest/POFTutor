package tech.alexontest.poftutor.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Abstract Test class that creates a new WebDriver for Each Test.
 * Supports JUnit 4 and JUnit 5 tests.
 */
abstract public class AbstractTest {
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

    @After
    @AfterEach
    public void teardown() {
        //close the webdriver
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
