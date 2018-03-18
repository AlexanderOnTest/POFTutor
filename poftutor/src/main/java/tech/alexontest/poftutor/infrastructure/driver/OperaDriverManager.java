package tech.alexontest.poftutor.infrastructure.driver;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public final class OperaDriverManager extends AbstractDriverManager implements WebDriverManager {

    private static final String OPERA_VERSION = "51.0.2830.55";

    private OperaDriverService operaDriverService;

    private final File operaDriverExe;

    private final boolean isLocal;

    OperaDriverManager(final boolean isLocal) {
        final String path = getClass().getClassLoader().getResource("operadriver.exe").getPath();
        operaDriverExe = new File(path);
        System.setProperty("webdriver.opera.driver", path);
        this.isLocal = isLocal;
    }

    @Override
    public void startService() {
        if (null == operaDriverService) {
            try {
                operaDriverService = new OperaDriverService.Builder()
                        .usingDriverExecutable(operaDriverExe)
                        .usingAnyFreePort()
                        .build();
                operaDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("OperaDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != operaDriverService && operaDriverService.isRunning()) {
            operaDriverService.stop();
            System.out.println("OperaDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final OperaOptions options = new OperaOptions()
                .setBinary(new File("C:/Program Files/Opera/" + OPERA_VERSION + "/opera.exe"));
        // add additional options here as required
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
        } else {
            setDriver(new OperaDriver(options));
        }
        System.out.println("OperaDriver Started");
        return options.getBrowserName();
    }
}
