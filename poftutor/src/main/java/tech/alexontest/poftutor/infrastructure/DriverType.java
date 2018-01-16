package tech.alexontest.poftutor.infrastructure;

public enum DriverType {
    CHROME("Chrome"),
    CHROME_LOCAL("Chrome"),
    CHROME_LOCAL_HEADLESS("HeadlessChrome"),
    EDGE("Edge"),
    FIREFOX("Firefox"),
    IE("rv:11.0"),
    OPERA("OPR/"),
    OPERA_LOCAL("OPR/"),;

    private final String checkString;

    DriverType(final String checkString) {
        this.checkString = checkString;
    }

    public String getCheckString() {
        return checkString;
    }
}
