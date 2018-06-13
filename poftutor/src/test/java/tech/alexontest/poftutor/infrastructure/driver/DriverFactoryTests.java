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

package tech.alexontest.poftutor.infrastructure.driver;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractCrossBrowserTest;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfigurationFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Framework")
class DriverFactoryTests extends AbstractCrossBrowserTest {

    @ParameterizedTest(name = "{0} successfully launches.")
    //Run all DriverTypes
    @EnumSource(DriverType.class)
    //Examples of how to filter which tests to run
    //  Debugging one that is failing.
    //@EnumSource(value = DriverType.class, names = "FIREFOX_MACOS")
    //  Exclude some values.
    //@EnumSource(value = DriverType.class, mode = EXCLUDE, names = {"OPERA_LOCAL", "FIREFOX_LOCAL"})
    //  Match a set of values e.g. run all local browsers only.
    //@EnumSource(value = DriverType.class, mode = MATCH_ALL, names = "^.*FIREFOX.*$")
    void driverFactoryWorks(final DriverType driverType) {
        // have to get the URL of the grid from config.json and set it in the DriverManagerFactory
        DriverManagerFactory.setGridUrl(TestConfigurationFactory.getTestConfiguration().getGridUrl());
        final String browserName = driverType.getWebdriverName();

        setDriverManager(DriverManagerFactory.getManager(driverType));
        getDriverManager().startService();

        assertThat(getDriverManager().createDriver())
                .as(String.format("Checking That browser is of type %s", browserName))
                .isEqualToIgnoringCase(browserName);

        final String homePageURL = "https://www.example.com/";
        final WebDriver driver = getDriver(homePageURL);
        final String agentString = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");

        assertThat(agentString)
                .as("AgentString does not match pattern for %s", driverType.name())
                .containsPattern(driverType.getRegex());

        assertThat(driver.getCurrentUrl())
                .as(String.format(
                        "Check that '%1$s' successfully loads the homepage '%2$s'", driverType.name(), homePageURL
                ))
                .isEqualToIgnoringCase(homePageURL);
    }
}
