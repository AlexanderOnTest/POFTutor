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

package tech.alexontest.poftutor.infrastructure;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public final class HttpTools {

    private HttpTools() { }

    /**
     * Crude check that the url leads to a valid page.
     * @param url the url to check
     */
    public static void assertLinkIsNotBroken(final String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            final int responseCode = conn.getResponseCode();
            assertThat(responseCode)
                    .as(String.format("Link '%s' did not return a valid response. Response code: %d",
                            url,
                            responseCode))
                    .isEqualTo(HttpURLConnection.HTTP_OK);
        } catch (final IOException e) {
            throw new AssertionError(String.format("Link '%s' does not return a valid response", url));
        }
    }
}
