package tech.alexontest.poftutor.infrastructure;

public class DriverManagerFactory {

    private DriverManagerFactory() { }

    public static AbstractDriverManager getManager(final DriverType type) {

        AbstractDriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
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
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}