package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class EdgeDriverManager extends AbstractDriverManager implements WebDriverManager {
    private EdgeDriverService edgeDriverService;
    private final File edgeDriverExe;

    EdgeDriverManager() {
        final String path = getClass().getClassLoader().getResource("MicrosoftWebDriver.exe").getPath();
        edgeDriverExe = new File(path);
        System.setProperty("webdriver.edge.driver", path);
    }

    @Override
    public void startService() {
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
        this.driver = new RemoteWebDriver(getGridUrl(), options);

        System.out.println("EdgeDriver Started");
        return DesiredCapabilities.edge().getBrowserName();
    }
}