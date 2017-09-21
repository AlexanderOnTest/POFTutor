package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class FirefoxDriverManager extends AbstractDriverManager {
    private GeckoDriverService geckoDriverService;
    private File geckoDriverExe;

    FirefoxDriverManager() {
        String path = getClass().getClassLoader().getResource("geckodriver.exe").getPath();
        geckoDriverExe = new File(path);
        System.setProperty("webdriver.gecko.driver", path);
    }

    @Override
    public void startService() {
        if (null == geckoDriverService) {
            try {
                geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(geckoDriverExe)
                        .usingAnyFreePort()
                        .build();
                geckoDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != geckoDriverService && geckoDriverService.isRunning())
            geckoDriverService.stop();
    }

    @Override
    public String createDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        // add capabilities
        final FirefoxOptions options = new FirefoxOptions();
        // add options
        options.addCapabilities(capabilities);
        driver = new FirefoxDriver(options);
        System.out.println("Driver Started");
        return capabilities.getBrowserName();
    }

}
