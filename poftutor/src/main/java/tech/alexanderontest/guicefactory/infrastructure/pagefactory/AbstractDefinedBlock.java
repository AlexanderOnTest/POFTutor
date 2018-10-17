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

package tech.alexanderontest.guicefactory.infrastructure.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Abstract super class to aid in the construction of blocks defined by a @FindBy defined rootElement.
 * Implementations are required to contain a rootElement field annotated with @FindBy.
 * These blocks are safe to use throughout an AJAX application as the rootElement is proxied.
 */
@SuppressWarnings("squid:S1610")
public abstract class AbstractDefinedBlock implements Block {
    protected AbstractDefinedBlock(final WebDriver webDriver) {
        final ElementLocatorFactory locatorFactory = new BlockingElementLocatorFactory(webDriver, this);
        PageFactory.initElements(locatorFactory, this);
    }

    @Override
    public WebElement getRootElement() {
        final WebElement rootElement;
        try {
            final Field f = this.getClass().getDeclaredField("rootElement");
            f.setAccessible(true);
            rootElement = (WebElement) f.get(this);
        } catch (final IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalStateException("A Block should have a WebElement rootElement" + e);
        }
        return rootElement;
    }
}
