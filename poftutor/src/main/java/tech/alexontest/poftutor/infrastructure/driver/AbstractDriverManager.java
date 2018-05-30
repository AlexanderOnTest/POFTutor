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

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractDriverManager implements WebDriverManager {
    private WebDriver driver;

    private URL gridUrl;

    AbstractDriverManager() {
        try {
            gridUrl = new URL("http://SILENTHTPC:4444/wd/hub");
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This implementation returns the default grid URL initialised in the constructor.
     * @return URL of the grid
     */
    URL getGridUrl() {
        return gridUrl;
    }

    /**
     * Close the WebDriver and Browser.
     */
    @Override
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            System.out.println("WebDriver quit");
            driver = null;
        }
    }

    /**
     * Return a running WebDriver instance controlling and instance of the requested browser.
     * @return the WebDriver to control the browser.
     */
    @Override
    public WebDriver get() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }

    final void setDriver(final WebDriver driver) {
        this.driver = driver;
    }
}
