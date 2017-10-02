package tech.alexontest.poftutor.infrastructure;

import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

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
        driver = new RemoteWebDriver(getGridUrl(), options);
        System.out.println("InternetExplorerDriver Started");
        return DesiredCapabilities.internetExplorer().getBrowserName();
    }
}