package tech.alexontest.poftutor.infrastructure;

        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeDriverService;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.remote.DesiredCapabilities;

        import java.io.File;
        import java.io.IOException;

public class ChromeDriverManager extends AbstractDriverManager {

    private ChromeDriverService chromeDriverService;
    private File chromedriverExe;

    ChromeDriverManager() {
        chromedriverExe = new File(getClass().getClassLoader().getResource("chromedriver.exe").getPath());
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
        }
    }

    @Override
    public void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning())
            chromeDriverService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type", "--start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.out.println("Driver Started");
        driver = new ChromeDriver(chromeDriverService, capabilities);
    }
}