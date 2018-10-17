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

package tech.alexanderontest.guicefactory.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import tech.alexanderontest.guicefactory.infrastructure.driver.DriverFactoryModule;
import tech.alexanderontest.guicefactory.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that creates a new configured WebDriver for each test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractMultipleWebDriverTest {
    // As I want to create multiple injector instances of the configured type I can reuse the module
    private DriverFactoryModule driverFactoryModule;

    @Inject
    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        driverFactoryModule = new DriverFactoryModule();
    }

    @BeforeEach
    void setup() {
        Guice.createInjector(driverFactoryModule)
                .injectMembers(this);
    }

    @AfterEach
    void teardown() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
    }

    @AfterAll
    void shutdown() {
        driverManager.stopService();
    }
}
