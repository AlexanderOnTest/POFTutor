package tech.alexanderontest.guicefactory.infrastructure.configuration;

import org.awaitility.Duration;
import tech.alexanderontest.guicefactory.infrastructure.driver.DriverType;

import java.net.URL;

public interface TestConfiguration {
    String getHomePageUrl();

    Duration getWaitTimeout();

    URL getGridUrl();

    DriverType getDriverType();
}
