package tech.alexontest.poftutor.infrastructure.driver;

import java.util.regex.Pattern;

@SuppressWarnings("squid:S1192")
public enum DriverType {
    CHROME("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bChrome/\\b.*?\\bSafari/\\b.*$", "chrome"),
    CHROME_LOCAL("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bChrome/\\b.*?\\bSafari/\\b.*$", "chrome"),
    CHROME_LOCAL_HEADLESS("HeadlessChrome", "chrome"),
    CHROME_MACOS("Macintosh; Intel Mac OS X\\b.*?\\bChrome", "chrome"),
    EDGE("Edge", "MicrosoftEdge"),
    EDGE_LOCAL("Edge", "MicrosoftEdge"),
    FIREFOX("Firefox", "firefox"),
    FIREFOX_LOCAL("Firefox", "firefox"),
    FIREFOX_LOCAL_HEADLESS("Firefox", "firefox"),
    FIREFOX_MACOS("Macintosh; Intel Mac OS X\\b.*?\\bGecko/.*?\\bFirefox/", "firefox"),
    IE("rv:11.0", "internet explorer"),
    IE_LOCAL("rv:11.0", "internet explorer"),
    OPERA_LOCAL("OPR/", "operablink"),
    SAFARI_MACOS("Macintosh; Intel Mac OS X\\b.*?\\bVersion/.*?\\bSafari/", "safari"),;

    private final String webdriverName;

    private final Pattern regex;

    DriverType(final String regexString, final String webdriverName) {
        this.regex = Pattern.compile(regexString);
        this.webdriverName = webdriverName;
    }

    public Pattern getRegex() {
        return regex;
    }

    public String getWebdriverName() {
        return webdriverName;
    }
}
