package tech.alexontest.poftutor.infrastructure.driver;

import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfigurationFactory;

public class DriverFactoryModule extends AbstractModule {

    private final TestConfiguration testConfiguration;

    private final DriverManagerFactory driverManagerFactory;

    public DriverFactoryModule() {
        this.testConfiguration = TestConfigurationFactory.getTestConfiguration();
        this.driverManagerFactory = new DriverManagerFactory(this.testConfiguration);
    }

    @Override
    protected void configure() {
        final WebDriverManager webDriverManager = driverManagerFactory.get();
        bind(TestConfiguration.class).toInstance(testConfiguration);
        bind(WebDriverManager.class).toInstance(webDriverManager);
        bind(WebDriver.class).toProvider(webDriverManager);
    }
}

