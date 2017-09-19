package tech.alexontest.poftutor.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static tech.alexontest.poftutor.infrastructure.DriverType.CHROME;

/**
 * Abstract Test class that creates a new WebDriver for Each Test.
 * Supports JUnit 4 and JUnit 5 tests.
 */
abstract public class AbstractTest {
    private WebDriver driver;
    private AbstractDriverManager driverManager;

    @Before
    @BeforeEach
    public void setup() {
        System.out.println("Preparing AbstractDriverManager");
        driverManager = DriverManagerFactory.getManager(CHROME);
        System.out.println("Getting Driver");
        driver = driverManager.getDriver();
    }

    @After
    @AfterEach
    public void teardown() {
        System.out.println("Quiting Driver");
        driverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
