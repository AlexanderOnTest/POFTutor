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

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.driver.WebDriverManager;

/**
 * Abstract Test class that can use different browsers within a single execution.
 * Used for testing the Framework itself. Not suitable for injecting dependencies.
 */
public abstract class AbstractCrossBrowserTest {
    private WebDriverManager driverManager;

    /**
     * Teardown activities. Quit the driver and stop the driver service.
     */
    @AfterEach
    public void teardown() {
        System.out.println("Quitting WebDriver");
        driverManager.quitDriver();
        driverManager.stopService();
    }

    /**
     * Provision a WebDriverManager instance.
     * @return The appropriate WebDriverManager class
     */
    protected WebDriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Set driverManager.
     * @param driverManager driverManager to set.
     */
    protected void setDriverManager(final WebDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    protected WebDriver getDriver(final String url) {
        final WebDriver driver = driverManager.get();
        driver.get(url);
        return driver;
    }
}
