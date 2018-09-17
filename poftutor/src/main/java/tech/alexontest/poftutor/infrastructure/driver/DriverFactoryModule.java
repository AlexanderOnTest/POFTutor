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

import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfigurationFactory;

public class DriverFactoryModule extends AbstractModule {

    private final TestConfiguration testConfiguration;

    private final DriverManagerFactory driverManagerFactory;

    public DriverFactoryModule() {
        this.testConfiguration = TestConfigurationFactory.getTestConfiguration();
        DriverManagerFactory.setGridUrl(testConfiguration.getGridUrl());
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

