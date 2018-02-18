package tech.alexontest.poftutor.infrastructure.driver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public final class FirefoxDriverManager extends AbstractDriverManager implements WebDriverManager {

    private GeckoDriverService geckoDriverService;

    private final File geckoDriverExe;

    private final boolean isLocal;

    private final boolean isHeadless;

    FirefoxDriverManager(final boolean isLocal, final boolean isHeadless) {
        final String path = getClass().getClassLoader().getResource("geckodriver.exe").getPath();
        geckoDriverExe = new File(path);
        System.setProperty("webdriver.gecko.driver", path);
        this.isLocal = isLocal;
        this.isHeadless = isHeadless;
    }

    @Override
    public void startService() {
        if (!isLocal) {
            return;
        }
        if (null == geckoDriverService) {
            try {
                geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(geckoDriverExe)
                        .usingAnyFreePort()
                        .build();
                geckoDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("GeckoDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != geckoDriverService && geckoDriverService.isRunning()) {
            geckoDriverService.stop();
            System.out.println("GeckoDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final FirefoxOptions options = new FirefoxOptions()
                .setLogLevel(FirefoxDriverLogLevel.ERROR);
        //to stop the debug spam
        // add additional options here as required
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
        } else {
            if (isHeadless) {
                options.addArguments("--headless");
            }
            setDriver(new FirefoxDriver(geckoDriverService, options));
        }
        System.out.println("FirefoxDriver Started");
        return options.getBrowserName();
    }
}

