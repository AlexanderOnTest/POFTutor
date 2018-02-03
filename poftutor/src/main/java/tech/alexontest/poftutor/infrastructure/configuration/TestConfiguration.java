package tech.alexontest.poftutor.infrastructure.configuration;

import tech.alexontest.poftutor.infrastructure.driver.DriverType;

import java.time.Duration;

public interface TestConfiguration {
    String getHomePageUrl();

    Duration getWaitTimeout();

    DriverType getDriverType();
}
