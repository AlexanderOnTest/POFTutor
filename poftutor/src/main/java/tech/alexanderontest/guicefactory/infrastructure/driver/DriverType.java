/*
 * Copyright (c) 2018. Alexander Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.alexanderontest.guicefactory.infrastructure.driver;

import java.util.regex.Pattern;

@SuppressWarnings("squid:S1192")
public enum DriverType {
    CHROME("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bWindows NT.*?\\bChrome/\\b.*?\\bSafari/\\b.*$", "chrome"),
    CHROME_LOCAL("^(?!.*?\\bOPR/\\b)^(?!.*?\\bEdge/\\b).*?\\bChrome/\\b.*?\\bSafari/\\b.*$", "chrome"),
    CHROME_LOCAL_HEADLESS("HeadlessChrome", "chrome"),
    CHROME_MACOS("Macintosh; Intel Mac OS X\\b.*?\\bChrome", "chrome"),
    EDGE("Edge", "MicrosoftEdge"),
    EDGE_LOCAL("Edge", "MicrosoftEdge"),
    FIREFOX("Windows NT\\b.*?\\bGecko/.*?\\bFirefox/", "firefox"),
    FIREFOX_LOCAL("Firefox", "firefox"),
    FIREFOX_LOCAL_HEADLESS("Firefox", "firefox"),
    FIREFOX_MACOS("Macintosh; Intel Mac OS X\\b.*?\\bGecko/.*?\\bFirefox/", "firefox"),
    IE("rv:11.0", "internet explorer"),
    IE_LOCAL("rv:11.0", "internet explorer"),
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
