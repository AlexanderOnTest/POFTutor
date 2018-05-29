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

package tech.alexontest.poftutor.infrastructure.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import java.io.IOException;

public final class TestConfigurationFactory {
    private TestConfigurationFactory() { }

    public static TestConfiguration getTestConfiguration() {
        final TestConfiguration testConfiguration;
        try {
            testConfiguration = new Gson().fromJson(
                    Resources.toString(Resources.getResource("config.json"), Charsets.UTF_8),
                    TestConfigurationImpl.class);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Could not load 'config.json'");
        }
        return testConfiguration;
    }
}
