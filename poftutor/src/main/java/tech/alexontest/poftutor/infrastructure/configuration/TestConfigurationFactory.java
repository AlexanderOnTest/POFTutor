package tech.alexontest.poftutor.infrastructure.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import java.io.IOException;

public final class TestConfigurationFactory {
    private TestConfigurationFactory() { }

    public static TestConfiguration getTestConfiguration() {
        final TestConfiguration testConfiguration;
        try {
            testConfiguration = new Gson().fromJson(
                    Resources.toString(Resources.getResource("config.json"), Charsets.UTF_8),
                    TestConfigurationImpl.class);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Could not load 'config.json'");
        }
        return testConfiguration;
    }
}
