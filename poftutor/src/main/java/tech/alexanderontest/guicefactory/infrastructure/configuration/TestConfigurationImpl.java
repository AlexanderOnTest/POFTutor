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

import org.awaitility.Duration;
import tech.alexanderontest.guicefactory.infrastructure.driver.DriverType;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestConfigurationImpl implements TestConfiguration {
    private String homePageUrl;

    private Duration waitTimeout;

    private URL gridUrl;

    private DriverType driverType;

    /**
     * This constructor replaces any value from the configuration file with an environment variable value.
     * @param fileConfiguration the TestConfigurationImpl generated from a json file.
     */
    TestConfigurationImpl(final TestConfiguration fileConfiguration) {
        this.homePageUrl = getEnvVariableOrDefaultHomePageUrl(fileConfiguration.getHomePageUrl());
        this.waitTimeout = getEnvVariableOrDefaultTimeout(fileConfiguration.getWaitTimeout());
        this.gridUrl = getEnvVariableOrDefaultGridUrl(fileConfiguration.getGridUrl());
        this.driverType = getEnvVariableOrDefaultDriverType(fileConfiguration.getDriverType());
    }

    @Override
    public String getHomePageUrl() {
        return homePageUrl;
    }

    @Override
    public Duration getWaitTimeout() {
        return waitTimeout;
    }

    @Override
    public URL getGridUrl() {
        return gridUrl;
    }

    @Override
    public DriverType getDriverType() {
        return driverType;
    }

    private static String getEnvVariableOrDefaultHomePageUrl(final String configFileValue) {
        final String valueFromEnvironment = System.getenv(TestConfigurationFactory.HOMEPAGE_URL_KEY);
        return valueFromEnvironment != null ? valueFromEnvironment : configFileValue;
    }

    private static Duration getEnvVariableOrDefaultTimeout(final Duration configFileValue) {
        int valueFromEnvironment = 0;
        final String stringFromEnvironment = System.getenv(TestConfigurationFactory.WAIT_TIMEOUT_KEY);
        if (stringFromEnvironment != null) {
            try {
                valueFromEnvironment = Integer.parseInt(stringFromEnvironment);
            } catch (final NumberFormatException e) {
                throw new IllegalArgumentException(
                        "The value of the environment variable 'waitTimeout' should be a parsable integer but was not.",
                        e);
            }
        }
        return valueFromEnvironment > 0 ? new Duration(valueFromEnvironment, SECONDS) : configFileValue;
    }

    private static URL getEnvVariableOrDefaultGridUrl(final URL configFileValue) {
        URL valueFromEnvironment = null;
        final String stringFromEnvironment = System.getenv(TestConfigurationFactory.GRID_URL_KEY);
        if (stringFromEnvironment != null) {
            try {
                valueFromEnvironment = new URL(stringFromEnvironment);
            } catch (final MalformedURLException e) {
                throw new IllegalArgumentException(
                        "The value of the environment variable 'gridUrl' is not a valid URL.", e);
            }
        }
        return valueFromEnvironment != null ? valueFromEnvironment : configFileValue;
    }

    private static DriverType getEnvVariableOrDefaultDriverType(final DriverType configFileValue) {
        DriverType valueFromEnvironment = null;
        final String stringFromEnvironment = System.getenv(TestConfigurationFactory.DRIVERTYPE_KEY);
        if (stringFromEnvironment != null) {
            try {
                valueFromEnvironment = DriverType.valueOf(stringFromEnvironment);
            } catch (final IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "The value of the environment variable 'driverType' is not a valid DriverType.", e);
            }
        }
        return valueFromEnvironment != null ? valueFromEnvironment : configFileValue;
    }
}
