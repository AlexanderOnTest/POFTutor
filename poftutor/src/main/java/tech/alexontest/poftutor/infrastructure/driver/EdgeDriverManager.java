package tech.alexontest.poftutor.infrastructure.driver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public final class EdgeDriverManager extends AbstractDriverManager implements WebDriverManager {

    private EdgeDriverService edgeDriverService;

    private final File edgeDriverExe;

    private final boolean isLocal;

    EdgeDriverManager(final boolean isLocal) {
        final String path = getClass().getClassLoader().getResource("MicrosoftWebDriver.exe").getPath();
        edgeDriverExe = new File(path);
        System.setProperty("webdriver.edge.driver", path);
        this.isLocal = isLocal;
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
