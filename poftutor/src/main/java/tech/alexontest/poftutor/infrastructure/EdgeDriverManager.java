package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class EdgeDriverManager extends AbstractDriverManager implements WebDriverManager {

    private EdgeDriverService edgeDriverService;
    private File edgeDriverExe;

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        quitDriver();
        if (null != edgeDriverService && edgeDriverService.isRunning())
            edgeDriverService.stop();
    }

    @Override
    public String createDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.edge();
        // add capabilities
        final EdgeOptions options = new EdgeOptions();
        // add options
        capabilities.setCapability(EdgeOptions.CAPABILITY, options);
        System.out.println("Driver Started");
        driver = new EdgeDriver(capabilities);
        return capabilities.getBrowserName();
    }
}