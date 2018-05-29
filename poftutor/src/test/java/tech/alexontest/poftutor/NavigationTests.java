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

package tech.alexontest.poftutor;

import com.google.inject.Inject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.alexontest.poftutor.infrastructure.AbstractSingleWebDriverTest;
import tech.alexontest.poftutor.steps.HomePageSteps;

@Tag("Navigation")
class NavigationTests extends AbstractSingleWebDriverTest {

    @Inject
    private HomePageSteps homePageSteps;

    @ParameterizedTest(name = "{0} returns 'https://alexanderontesting.com' as expected.")
    @CsvSource({
            "https://alexanderontesting.com/",
            //"http://alexanderontesting.com/", //TODO fix http redirection w/o breaking others
            "https://www.alexanderontesting.com/",
            "http://www.alexanderontesting.com/",
            "https://alexontest.tech/",
            "http://alexontest.tech/",
            "https://www.alexontest.tech/",
            "http://www.alexontest.tech/",
    })

    void allUrlsLeadToHere(final String urlToTest) {
        homePageSteps
                .attemptToLoadHomePageFromUrl(urlToTest)
                .assertThatReportedUrlIsCorrect();
    }
}
