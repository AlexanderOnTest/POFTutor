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

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public final class SafariDriverManager extends AbstractDriverManager implements WebDriverManager {

    private final boolean isLocal;

    private final boolean isHeadless;

    SafariDriverManager(final boolean isLocal, final boolean isHeadless) {
        this.isLocal = isLocal;
        this.isHeadless = isHeadless;
    }

    @Override
    public void startService() {
        if (isLocal) {
            throw new UnsupportedOperationException("Local running is only supported on Microsoft Windows."
                    + " Please use the grid.");
        }
    }

    @Override
    public void stopService() {
        if (isLocal) {
            throw new UnsupportedOperationException("Local running is only supported on Microsoft Windows."
                    + " Please use the grid.");
        }
    }

    @Override
    public String createDriver() {
        final SafariOptions options = new SafariOptions();
        // add additional options here as required
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
            options.setCapability("platform", "MAC");
        } else {
            if (isHeadless) {
                throw new UnsupportedOperationException("Safari does not support headless testing.");
            }
        }
        System.out.println("SafariDriver Started");
        get().manage().window().maximize();
        return options.getBrowserName();
    }
}

