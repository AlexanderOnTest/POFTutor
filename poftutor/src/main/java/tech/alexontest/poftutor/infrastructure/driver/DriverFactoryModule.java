package tech.alexontest.poftutor.infrastructure.driver;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfigurationImpl;

import java.io.IOException;

public class DriverFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        final TestConfiguration testConfiguration;
        try {
            testConfiguration = new Gson().fromJson(
                    Resources.toString(Resources.getResource("config.json"), Charsets.UTF_8),
                    TestConfigurationImpl.class);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Could not load 'config.json'");
        }
        final WebDriverManager webDriverManager = new DriverManagerFactory(testConfiguration).get();
        bind(TestConfiguration.class).toInstance(testConfiguration);
        bind(WebDriverManager.class).toInstance(webDriverManager);
        bind(WebDriver.class).toProvider(webDriverManager);
    }
}

