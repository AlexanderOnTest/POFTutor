package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FirefoxDriverManager extends AbstractDriverManager implements WebDriverManager {
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
        quitDriver();
        if (null != geckoDriverService && geckoDriverService.isRunning())
            geckoDriverService.stop();
    }

    @Override
    public String createDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        // add capabilities
        final FirefoxOptions options = new FirefoxOptions().setLogLevel(Level.OFF);
        // add options
        options.addCapabilities(capabilities);
        driver = new FirefoxDriver(capabilities);
        System.out.println("Driver Started");
        return capabilities.getBrowserName();
    }

}
