package tech.alexanderontest.guicefactory.infrastructure.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class TestConfigurationFactory {
    static final String HOMEPAGE_URL_KEY = "GF_homePageUrl";

    static final String WAIT_TIMEOUT_KEY = "GF_waitTimeout";

    static final String GRID_URL_KEY = "GF_gridUrl";

    static final String DRIVERTYPE_KEY = "GF_driverType";

    private TestConfigurationFactory() { }

    /**
     * Generate a configuration based (in increasing order of preference) on:
     * the default configuration file (config.json) in the repository main resources OR
     * a file called config.json in the ''Documents folder of the user's home directory.
     * The individual properties from the config file can be overridden using the environment variables:
     * "GF_homePageUrl", "GF_gridUrl", "GF_waitTimeout" or "GF_driverType"
     * @return a configured TestConfiguration
     */
    public static TestConfiguration getTestConfiguration() {
        final TestConfiguration fileConfiguration;
        try {
            final File userConfig = new File(System.getProperty("user.home")
                    + File.separator + "Documents" + File.separator + "config.json");
            final URL configFile = (userConfig.exists() && !userConfig.isDirectory())
                    ? userConfig.toURI().toURL()
                    : Resources.getResource("config.json");
            //TODO check config file exists and apply default defaults and log message.
            fileConfiguration = new Gson().fromJson(
                    Resources.toString(configFile, Charsets.UTF_8),
                    TestConfigurationImpl.class);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Could not load 'config.json'");
        }
        // Generate the final config using Environment Variable values if present.
        return new TestConfigurationImpl(fileConfiguration);
    }
}
