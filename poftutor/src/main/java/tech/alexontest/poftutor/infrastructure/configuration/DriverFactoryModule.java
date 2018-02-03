package tech.alexontest.poftutor.infrastructure.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import tech.alexontest.poftutor.infrastructure.driver.DriverManagerFactory;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

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
        bind(TestConfiguration.class).toInstance(testConfiguration);
        bind(WebDriverManager.class).toProvider(DriverManagerFactory.class).asEagerSingleton();
    }
}

