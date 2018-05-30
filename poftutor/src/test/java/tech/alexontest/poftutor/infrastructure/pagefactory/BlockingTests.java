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

package tech.alexontest.poftutor.infrastructure.pagefactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Component tests verifying that the correct ElementLocator implementation is returned for each type of field.
 * Verifies the correct interaction of the BlockingElementLocatorFactory and Abstract****Block implementations.
 * FoundBlock implementations should return a DefaultElementLocator for all fields.
 * DefinedBlock implementations should return
 *         * a DefaultElementLocator for rootElement and
 *         * a BlockingElementLocator for all other fields
 */
@Tag("Blocking")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlockingTests {
    private WebDriver webDriver;

    private Block definedBlock;

    private WebElement webElement;

    @BeforeAll
    void setup() {
        webDriver = mock(WebDriver.class);
        definedBlock = new DefinedBlock(webDriver);
        webElement = mock(WebElement.class);
    }

    @Test
    @DisplayName("In a defined Block, the rootElement field returns a DefaultElementLocator")
    void definedBlockRootElementReturnsDefaultEL() throws NoSuchFieldException {
        final ElementLocatorFactory elementLocatorFactory =
                new BlockingElementLocatorFactory(webDriver, definedBlock);
        final Field rootElement = DefinedBlock.class.getDeclaredField("rootElement");
        final ElementLocator elementLocator = elementLocatorFactory.createLocator(rootElement);
        assertThat(elementLocator)
                .as("rootElement did not generate a DefaultElementLocator")
                .isInstanceOf(DefaultElementLocator.class);
    }

    @Test
    @DisplayName("In a defined Block, a non-rootElement field returns a BlockingElementLocator")
    void definedBlockNonRootElementReturnsBlockingEL() throws NoSuchFieldException {
        final ElementLocatorFactory elementLocatorFactory =
                new BlockingElementLocatorFactory(webDriver, definedBlock);
        final Field nonRootElement = DefinedBlock.class.getDeclaredField("nonRootElement");
        final ElementLocator elementLocator = elementLocatorFactory.createLocator(nonRootElement);
        assertThat(elementLocator)
                .as("non-rootElement did not generate a BlockingElementLocator")
                .isInstanceOf(BlockingElementLocator.class);
    }

    @Test
    @DisplayName("In a found Block, the rootElement field returns a DefaultElementLocator")
    void foundBlockNonRootElementReturnsBlockingEL() throws NoSuchFieldException {
        final ElementLocatorFactory elementLocatorFactory =
                new BlockingElementLocatorFactory(webElement);
        final Field nonRootElement = FoundBlock.class.getDeclaredField("nonRootElement");
        final ElementLocator elementLocator = elementLocatorFactory.createLocator(nonRootElement);
        assertThat(elementLocator)
                .as("non-rootElement did not generate a DefaultElementLocator")
                .isInstanceOf(DefaultElementLocator.class);
    }

    @Test
    @DisplayName("In a found Block, a non-rootElement field returns a DefaultElementLocator")
    void foundBlockRootElementReturnsBlockingEL() throws NoSuchFieldException {
        final ElementLocatorFactory elementLocatorFactory =
                new BlockingElementLocatorFactory(webElement);
        final Field rootElement = FoundBlock.class.getSuperclass().getDeclaredField("rootElement");
        final ElementLocator elementLocator = elementLocatorFactory.createLocator(rootElement);
        assertThat(elementLocator)
                .as("non-rootElement did not generate a DefaultElementLocator")
                .isInstanceOf(DefaultElementLocator.class);
    }

    private class FoundBlock extends AbstractFoundBlock {
        @FindBy
        private WebElement nonRootElement;

        FoundBlock(final WebElement rootElement) {
            super(rootElement);
        }
    }

    private class DefinedBlock extends AbstractDefinedBlock {
        @FindBy
        private WebElement rootElement;

        @FindBy
        private WebElement nonRootElement;

        DefinedBlock(final WebDriver webDriver) {
            super(webDriver);
        }

        @Override
        public WebElement getRootElement() {
            return rootElement;
        }
    }
}
