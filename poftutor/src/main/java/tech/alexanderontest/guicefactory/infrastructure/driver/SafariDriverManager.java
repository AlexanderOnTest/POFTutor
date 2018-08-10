package tech.alexanderontest.guicefactory.infrastructure.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;

public final class SafariDriverManager extends AbstractDriverManager implements WebDriverManager {

    SafariDriverManager(final URL gridUrl) {
        super(gridUrl);
    }

    @Override
    public void startService() {
        // Do nothing: Service is only required for local running - not supported.
    }

    @Override
    public void stopService() {
        // Do nothing: Service is only required for local running - not supported.
    }

    @Override
    public String createDriver() {
        final SafariOptions options = new SafariOptions();
        // add additional options here as required
        setDriver(new RemoteWebDriver(getGridUrl(), options));
        options.setCapability("platform", "MAC");
        System.out.println("SafariDriver Started");
        get().manage().window().maximize();
        return options.getBrowserName();
    }
}

