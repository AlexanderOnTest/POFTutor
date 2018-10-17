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
import org.openqa.selenium.Platform;
import tech.alexanderontest.guicefactory.infrastructure.configuration.TestConfiguration;

import java.net.URL;

public final class DriverManagerFactory implements Provider<WebDriverManager> {
    private static URL gridUrl;

    private final TestConfiguration testConfiguration;

    private WebDriverManager webDriverManager;

    DriverManagerFactory(final TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    static void setGridUrl(final URL gridUrl) {
        DriverManagerFactory.gridUrl = gridUrl;
    }

    @SuppressWarnings("CyclomaticComplexity")
    public static AbstractDriverManager getManager(final DriverType type) {

        final AbstractDriverManager driverManager;

        if (type == null) {
            throw new IllegalArgumentException("Requested DriverType is not recognised");
        }

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager(gridUrl, Platform.WIN10);
                break;

            case CHROME_LOCAL:
                driverManager = new ChromeDriverManager(false);
                break;

            case CHROME_LOCAL_HEADLESS:
                driverManager = new ChromeDriverManager(true);
                break;

            case CHROME_MACOS:
                driverManager = new ChromeDriverManager(gridUrl, Platform.MAC);
                break;

            case EDGE:
                driverManager = new EdgeDriverManager(gridUrl);
                break;

            case EDGE_LOCAL:
                driverManager = new EdgeDriverManager();
                break;

            case FIREFOX:
                driverManager = new FirefoxDriverManager(gridUrl, Platform.WIN10);
                break;

            case FIREFOX_LOCAL:
                driverManager = new FirefoxDriverManager(false);
                break;

            case FIREFOX_LOCAL_HEADLESS:
                driverManager = new FirefoxDriverManager(true);
                break;

            case FIREFOX_MACOS:
                driverManager = new FirefoxDriverManager(gridUrl, Platform.MAC);
                break;

            case IE:
                driverManager = new InternetExplorerDriverManager(gridUrl);
                break;

            case IE_LOCAL:
                driverManager = new InternetExplorerDriverManager();
                break;

            case SAFARI_MACOS:
                driverManager = new SafariDriverManager(gridUrl);
                break;

            default:
                throw new UnsupportedOperationException(
                        String.format("Requested Browser '%s' has not yet been implemented.",
                                type));
        }
        return driverManager;

    }

    @Override
    public WebDriverManager get() {
        if (webDriverManager == null) {
            webDriverManager = getManager(testConfiguration.getDriverType());
        }
        return webDriverManager;
    }
}
