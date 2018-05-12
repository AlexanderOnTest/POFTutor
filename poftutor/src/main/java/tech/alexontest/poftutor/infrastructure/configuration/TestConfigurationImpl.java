package tech.alexontest.poftutor.infrastructure.configuration;

import org.awaitility.Duration;
import tech.alexontest.poftutor.infrastructure.driver.DriverType;

public class TestConfigurationImpl implements TestConfiguration {
    private String homePageUrl;

    private Duration waitTimeout;

    private DriverType driverType;

    @Override
    public String getHomePageUrl() {
        return homePageUrl;
    }

    @Override
    public Duration getWaitTimeout() {
        return waitTimeout;
    }

    @Override
    public DriverType getDriverType() {
        return driverType;
    }
}

