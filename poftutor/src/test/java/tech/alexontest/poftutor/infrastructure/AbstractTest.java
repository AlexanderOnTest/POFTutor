package tech.alexontest.poftutor.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.DriverFactoryModule;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * AbstractTest class that reuses the same WebDriver for all tests.
 * Only supports JUnit 5 tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {
    private WebDriver webDriver;

    @Inject
    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        final Injector injector = Guice.createInjector(new DriverFactoryModule());
        injector.injectMembers(this);
        webDriver = driverManager.getDriver();
        System.out.println("Getting Driver");
    }

    @AfterAll
    void finalise() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
        driverManager.stopService();
    }

    protected WebDriver getDriver(final String url) {
        webDriver.get(url);
        return webDriver;
    }
}
