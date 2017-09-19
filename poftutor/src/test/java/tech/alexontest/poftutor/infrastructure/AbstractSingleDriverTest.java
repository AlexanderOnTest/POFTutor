package tech.alexontest.poftutor.infrastructure;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Abstract test class that reuses the same WebDriver for all tests.
 * Only supports JUnit 5 tests.
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract public class AbstractSingleDriverTest {
    private WebDriver driver;

    @BeforeClass
    @BeforeAll
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

    @AfterClass
    @AfterAll
    public void teardown() {
        //close the webdriver
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
