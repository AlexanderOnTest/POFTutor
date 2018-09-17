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

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class InternetExplorerDriverManager extends AbstractDriverManager implements WebDriverManager {

    private InternetExplorerDriverService internetExplorerDriverService;

    private final File internetExplorerDriverExe;

    private final boolean isLocal;

    InternetExplorerDriverManager() {
        super(null);
        final String path = getClass().getClassLoader().getResource("IEDriverServer.exe").getPath();
        internetExplorerDriverExe = new File(path);
        System.setProperty("webdriver.ie.driver", path);
        this.isLocal = true;
    }

    InternetExplorerDriverManager(final URL gridUrl) {
        super(gridUrl);
        internetExplorerDriverExe = null;
        this.isLocal = false;
    }

    @Override
    public void startService() {
        if (null == internetExplorerDriverService) {
            if (!isLocal) {
                return;
            }
            try {
                internetExplorerDriverService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(internetExplorerDriverExe)
                        .usingAnyFreePort()
                        .build();
                internetExplorerDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("InternetExplorerDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != internetExplorerDriverService && internetExplorerDriverService.isRunning()) {
            internetExplorerDriverService.stop();
            System.out.println("InternetExplorerDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final InternetExplorerOptions options = new InternetExplorerOptions();
        //add required options here
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
        } else {
            setDriver(new InternetExplorerDriver(internetExplorerDriverService, options));
        }
        System.out.println("InternetExplorerDriver Started");
        return options.getBrowserName();
    }
}
