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

package tech.alexontest.poftutor.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import tech.alexontest.poftutor.infrastructure.driver.DriverFactoryModule;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that reuses the same configured WebDriver for all tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractSingleWebDriverTest {
    @Inject
    private WebDriverManager driverManager;

    @BeforeAll
    void prepare() {
        final DriverFactoryModule driverFactoryModule = new DriverFactoryModule();
        Guice.createInjector(driverFactoryModule)
                .injectMembers(this);
    }

    @AfterAll
    void finalise() {
        System.out.println("Quitting Driver");
        driverManager.quitDriver();
        driverManager.stopService();
    }
}
