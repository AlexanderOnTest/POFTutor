package tech.alexanderontest.guicefactory.infrastructure.driver;

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
        // for Windows 10 version 1809 and later - this shouldn't be needed, but it's not being picked up from PATH
        final String path = "C:\\Windows\\System32\\MicrosoftWebDriver.exe";
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
