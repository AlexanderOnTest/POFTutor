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

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class EdgeDriverManager extends AbstractDriverManager implements WebDriverManager {

    private EdgeDriverService edgeDriverService;

    private final File edgeDriverExe;

    private final boolean isLocal;

    EdgeDriverManager(final URL gridUrl) {
        super(gridUrl);
        edgeDriverExe = null;
        this.isLocal = false;
    }

    EdgeDriverManager() {
        super(null);
        final String path = getClass().getClassLoader().getResource("MicrosoftWebDriver.exe").getPath();
        edgeDriverExe = new File(path);
        System.setProperty("webdriver.edge.driver", path);
        this.isLocal = true;
    }

    @Override
    public void startService() {
        if (!isLocal) {
            return;
        }
        if (null == edgeDriverService) {
            try {
                edgeDriverService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(edgeDriverExe)
                        .usingAnyFreePort()
                        .build();
                edgeDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("EdgeDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != edgeDriverService && edgeDriverService.isRunning()) {
            edgeDriverService.stop();
            System.out.println("EdgeDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final EdgeOptions options = new EdgeOptions();
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
        } else {
            setDriver(new EdgeDriver(edgeDriverService, options));
        }
        System.out.println("EdgeDriver Started");
        return options.getBrowserName();
    }
}
