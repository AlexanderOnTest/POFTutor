package tech.alexontest.poftutor.infrastructure;

public class DriverManagerFactory {

    private DriverManagerFactory() { }

    public static AbstractDriverManager getManager(DriverType type) {


        AbstractDriverManager driverManager;

        switch (type) {
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}