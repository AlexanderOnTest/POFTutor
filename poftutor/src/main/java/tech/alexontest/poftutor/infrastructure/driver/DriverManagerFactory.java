package tech.alexontest.poftutor.infrastructure.driver;

import com.google.inject.Provider;
import org.openqa.selenium.Platform;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;

public final class DriverManagerFactory implements Provider<WebDriverManager> {

    private final TestConfiguration testConfiguration;

    private WebDriverManager webDriverManager;

    DriverManagerFactory(final TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    @SuppressWarnings("CyclomaticComplexity")
    public static AbstractDriverManager getManager(final DriverType type) {

        final AbstractDriverManager driverManager;

        if (type == null) {
            throw new IllegalArgumentException("Requested DriverType is not recognised");
        }

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager(false, false);
                break;

            case CHROME_LOCAL:
                driverManager = new ChromeDriverManager(true, false);
                break;

            case CHROME_LOCAL_HEADLESS:
                driverManager = new ChromeDriverManager(true, true);
                break;

            case CHROME_MACOS:
                driverManager = new ChromeDriverManager(false, false, Platform.MAC);
                break;

            case EDGE:
                driverManager = new EdgeDriverManager(false);
                break;

            case EDGE_LOCAL:
                driverManager = new EdgeDriverManager(true);
                break;

            case FIREFOX:
                driverManager = new FirefoxDriverManager(false, false);
                break;

            case FIREFOX_LOCAL:
                driverManager = new FirefoxDriverManager(true, false);
                break;

            case FIREFOX_LOCAL_HEADLESS:
                driverManager = new FirefoxDriverManager(true, true);
                break;

            case FIREFOX_MACOS:
                driverManager = new FirefoxDriverManager(false, false, "MAC");
                break;

            case IE:
                driverManager = new InternetExplorerDriverManager(false);
                break;

            case IE_LOCAL:
                driverManager = new InternetExplorerDriverManager(true);
                break;

            case OPERA_LOCAL:
                driverManager = new OperaDriverManager(true);
                break;

            case SAFARI_MACOS:
                driverManager = new SafariDriverManager(false, false);
                break;

            default:
                throw new UnsupportedOperationException(
                        String.format("Requested Browser '%s' has not yet been implemented.",
                                type));
        }
        return driverManager;

    }

    @Override
    public WebDriverManager get() {
        if (webDriverManager == null) {
            webDriverManager = getManager(testConfiguration.getDriverType());
        }
        return webDriverManager;
    }
}
