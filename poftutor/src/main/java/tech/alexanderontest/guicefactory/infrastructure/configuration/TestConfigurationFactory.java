/*
 * Copyright (c) 2018. Alexander Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
