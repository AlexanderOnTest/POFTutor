package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class InternetExplorerDriverManager extends AbstractDriverManager implements WebDriverManager {

    private InternetExplorerDriverService internetExplorerDriverService;
    private final File internetExplorerDriverExe;

    InternetExplorerDriverManager() {
        final String path = getClass().getClassLoader().getResource("IEDriverServer.exe").getPath();
        internetExplorerDriverExe = new File(path);
        System.setProperty("webdriver.ie.driver", path);
    }

    @Override
    public void startService() {
        if (null == internetExplorerDriverService) {
            try {
                internetExplorerDriverService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(internetExplorerDriverExe)
                        .usingAnyFreePort()
                        .build();
                internetExplorerDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        quitDriver();
        if (null != internetExplorerDriverService && internetExplorerDriverService.isRunning())
            internetExplorerDriverService.stop();
    }

    @Override
    public String createDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        // add capabilities
        final InternetExplorerOptions options = new InternetExplorerOptions();
        //add options
        for (Map.Entry<String, ?> entry : capabilities.asMap().entrySet()) {
            options.setCapability(entry.getKey(), entry.getValue());
        }
        System.out.println("Driver Started");
        driver = new InternetExplorerDriver(options);
        return capabilities.getBrowserName();
    }
}