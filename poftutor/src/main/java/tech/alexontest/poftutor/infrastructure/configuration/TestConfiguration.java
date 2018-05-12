package tech.alexontest.poftutor.infrastructure.configuration;

import org.awaitility.Duration;
import tech.alexontest.poftutor.infrastructure.driver.DriverType;

public interface TestConfiguration {
    String getHomePageUrl();

    Duration getWaitTimeout();

    DriverType getDriverType();
}
