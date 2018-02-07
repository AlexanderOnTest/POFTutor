package tech.alexontest.poftutor.infrastructure.driver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import tech.alexontest.poftutor.infrastructure.configuration.TestConfiguration;

public final class DriverManagerFactory implements Provider<WebDriverManager> {
    private final TestConfiguration testConfiguration;

    @Inject
    DriverManagerFactory(final TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

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

            case EDGE:
                driverManager = new EdgeDriverManager();
                break;

            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;

            case IE:
                driverManager = new InternetExplorerDriverManager();
                break;

            case OPERA:
                driverManager = new OperaDriverManager(false);
                break;

            case OPERA_LOCAL:
                driverManager = new OperaDriverManager(true);
                break;

            default:
                driverManager = new ChromeDriverManager(true, false);
                break;
        }
        return driverManager;

    }

    @Override
    public WebDriverManager get() {
        return getManager(testConfiguration.getDriverType());
    }
}
