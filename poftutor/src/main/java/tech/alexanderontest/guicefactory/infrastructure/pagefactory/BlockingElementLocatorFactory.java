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

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class BlockingElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;

    private final Field rootElementField;

    /**
     * Instantiate a new ElementLocatorFactory that is functionally equivalent to the DefaultElementLocatorFactory.
     * @param searchContext the SearchContext to use as the rootElement of the Block. Typically a 'found' WebElement.
     */
    public BlockingElementLocatorFactory(final SearchContext searchContext) {
        this.searchContext = searchContext;
        rootElementField = null;
    }

    /**
     * Instantiate a new BlockingLocatorFactory that sets up a block using PageFactory annotations.
     * The rootElement of the block is defined by @FindBy annotation on the 'rootElement' field of the Block parameter.
     * @param driver A SearchContext (Typically WebDriver)
     * @param definedBlock A Block class where the rootElement field has a PageFactory @FindBy annotation
     */
    public BlockingElementLocatorFactory(final SearchContext driver, final Block definedBlock) {
        this.searchContext = driver;
        try {
            rootElementField = definedBlock.getClass().getDeclaredField("rootElement");
            // This constructor should only be used for Blocks with an annotated rootElement
            if (rootElementField.getAnnotationsByType(FindBy.class).length == 0) {
                throw new IllegalArgumentException("The 'rootElement' field does not have an @FindBy annotation.");
            }
        } catch (final NoSuchFieldException e) {
            throw new IllegalArgumentException("A defined Block requires a @FindBy annotated 'rootElement' field.", e);
        }
    }

    @Override
    public ElementLocator createLocator(final Field field) {
        return (rootElementField == null || field.getName().equals("rootElement"))
                ? new DefaultElementLocator(searchContext, field)
                : new BlockingElementLocator(searchContext, rootElementField, field);
    }
}
