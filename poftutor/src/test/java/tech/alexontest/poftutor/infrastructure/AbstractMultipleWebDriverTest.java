package tech.alexontest.poftutor.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import tech.alexontest.poftutor.infrastructure.driver.DriverFactoryModule;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that creates a new configured WebDriver for each test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractMultipleWebDriverTest {
    // As I want to create multiple injector instances of the configured type I can reuse the module
    private DriverFactoryModule driverFactoryModule;

    @Inject
    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        driverFactoryModule = new DriverFactoryModule();
    }

    @BeforeEach
    void setup() {
        Guice.createInjector(driverFactoryModule)
                .injectMembers(this);
    }

    @AfterEach
    void teardown() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
    }

    @AfterAll
    void shutdown() {
        driverManager.stopService();
    }
}
