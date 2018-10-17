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

package tech.alexanderontest.guicefactory.infrastructure.driver;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

/**
 * Interface for a generic WebDriverManager.
 */
public interface WebDriverManager extends Provider<WebDriver> {
    /**
     * Create a new WebDriver instance according to the configuration options.
     * @return the BrowserName that was requested in the options.
     */
    String createDriver();

    /**
     * Start the local WebDriver service if required.
     */
    void startService();

    /**
     * Stop the local WebDriver service if running.
     */
    void stopService();

    /**
     * Close the current WebDriver and Browser.
     */
    void quitDriver();

    /**
     * Return a running WebDriver instance controlling and instance of the requested browser.
     * @return the WebDriver to control the browser.
     */
    WebDriver get();
}
