package tech.alexontest.poftutor.infrastructure;

        import org.openqa.selenium.chrome.ChromeDriverService;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.remote.RemoteWebDriver;

        import java.io.File;
        import java.io.IOException;

public class ChromeDriverManager extends AbstractDriverManager implements WebDriverManager {

    private ChromeDriverService chromeDriverService;
    private final File chromedriverExe;

    ChromeDriverManager() {
        final String path = getClass().getClassLoader().getResource("chromedriver.exe").getPath();
        chromedriverExe = new File(path);
        System.setProperty("webdriver.chrome.driver", path);
    }

    @Override
    public void startService() {
        if (null == chromeDriverService) {
            try {
                chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromedriverExe)
                        .usingAnyFreePort()
                        .build();
                chromeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ChromeDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
            System.out.println("ChromeDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final ChromeOptions options = new ChromeOptions()
                .addArguments("test-type", "--start-maximized");
        // add additional required options here
        this.driver = new RemoteWebDriver(getGridUrl(), options);
        System.out.println("ChromeDriver Started");
        return DesiredCapabilities.chrome().getBrowserName();
    }
}