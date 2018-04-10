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
