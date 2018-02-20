package tech.alexontest.poftutor.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import tech.alexontest.poftutor.infrastructure.driver.DriverFactoryModule;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that reuses the same configured WebDriver for all tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractSingleWebDriverTest {
    @Inject
    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        final DriverFactoryModule driverFactoryModule = new DriverFactoryModule();
        Guice.createInjector(driverFactoryModule)
                .injectMembers(this);
    }

    @AfterAll
    void finalise() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
        driverManager.stopService();
    }
}
