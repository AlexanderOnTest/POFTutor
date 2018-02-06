package tech.alexontest.poftutor.infrastructure.driver;

import java.util.regex.Pattern;

public enum DriverType {
    CHROME("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bChrome/\\b.*?\\bSafari/\\b.*$"),
    CHROME_LOCAL("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bChrome/\\b.*?\\bSafari/\\b.*$"),
    CHROME_LOCAL_HEADLESS("HeadlessChrome"),
    EDGE("Edge"),
    FIREFOX("Firefox"),
    IE("rv:11.0"),
    OPERA("OPR/"),
    OPERA_LOCAL("OPR/"),;

    private final Pattern regex;

    DriverType(final String regexString) {
        this.regex = Pattern.compile(regexString);
    }

    public Pattern getRegex() {
        return regex;
    }
}
