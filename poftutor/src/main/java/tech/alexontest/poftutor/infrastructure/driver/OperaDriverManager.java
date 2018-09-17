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

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;
import java.io.IOException;

public final class OperaDriverManager extends AbstractDriverManager implements WebDriverManager {

    private static final String OPERA_VERSION = "52.0.2871.64";

    private OperaDriverService operaDriverService;

    private final File operaDriverExe;

    OperaDriverManager() {
        super(null);
        final String path = getClass().getClassLoader().getResource("operadriver.exe").getPath();
        operaDriverExe = new File(path);
        System.setProperty("webdriver.opera.driver", path);
    }

    @Override
    public void startService() {
        if (null == operaDriverService) {
            try {
                operaDriverService = new OperaDriverService.Builder()
                        .usingDriverExecutable(operaDriverExe)
                        .usingAnyFreePort()
                        .build();
                operaDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("OperaDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != operaDriverService && operaDriverService.isRunning()) {
            operaDriverService.stop();
            System.out.println("OperaDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final OperaOptions options = new OperaOptions()
                .setBinary(new File("C:/Program Files/Opera/" + OPERA_VERSION + "/opera.exe"));
        // add additional options here as required
        setDriver(new OperaDriver(options));
        System.out.println("OperaDriver Started");
        return options.getBrowserName();
    }
}
