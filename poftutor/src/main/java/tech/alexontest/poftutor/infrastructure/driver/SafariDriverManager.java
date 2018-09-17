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

import java.net.URL;

public final class SafariDriverManager extends AbstractDriverManager implements WebDriverManager {

    SafariDriverManager(final URL gridUrl) {
        super(gridUrl);
    }

    @Override
    public void startService() {
        // Do nothing: Service is only required for local running - not supported.
    }

    @Override
    public void stopService() {
        // Do nothing: Service is only required for local running - not supported.
    }

    @Override
    public String createDriver() {
        final SafariOptions options = new SafariOptions();
        // add additional options here as required
        setDriver(new RemoteWebDriver(getGridUrl(), options));
        options.setCapability("platform", "MAC");
        System.out.println("SafariDriver Started");
        get().manage().window().maximize();
        return options.getBrowserName();
    }
}

