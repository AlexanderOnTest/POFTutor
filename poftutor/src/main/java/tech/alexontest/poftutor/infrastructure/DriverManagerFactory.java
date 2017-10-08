package tech.alexontest.poftutor.infrastructure;

public final class DriverManagerFactory {

    private DriverManagerFactory() { }

    public static AbstractDriverManager getManager(final DriverType type) {

        final AbstractDriverManager driverManager;

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

            default:
                driverManager = new ChromeDriverManager(true, false);
                break;
        }
        return driverManager;

    }
}