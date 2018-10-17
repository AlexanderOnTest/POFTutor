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

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("Blocking")
class BlockingElementLocatorTests {

    private final By rootElementBy = By.id("re");

    private ElementLocator newLocator(final WebDriver driver, final Field field) throws NoSuchFieldException {
        final Field rootElementField = Block.class.getDeclaredField("rootElement");
        return new BlockingElementLocator(driver, rootElementField, field);
    }

    @Test
    void shouldFindRootElementThroughWebDriverInstanceToFindElement() throws Exception {
        final Field f = Block.class.getDeclaredField("first");
        final By by = new ByIdOrName("first");

        final WebElement element = mock(WebElement.class);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElement(by))
                .thenReturn(element);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        final WebElement returnedElement = locator.findElement();

        assertEquals(element, returnedElement);
    }

    @Test
    void shouldFindRootElementThroughWebDriverInstanceToFindElementList() throws Exception {
        final Field f = Block.class.getDeclaredField("list");
        final By by = new ByIdOrName("list");

        final WebElement element1 = mock(WebElement.class, "webElement1");
        final WebElement element2 = mock(WebElement.class, "webElement2");
        final List<WebElement> list = Arrays.asList(element1, element2);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElements(by))
                .thenReturn(list);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        final List<WebElement> returnedList = locator.findElements();

        assertEquals(list, returnedList);
    }

    @Test
    void cachedElementShouldBeCached() throws Exception {
        final Field f = Block.class.getDeclaredField("cached");
        final By by = new ByIdOrName("cached");

        final WebElement element = mock(WebElement.class);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElement(by))
                .thenReturn(element);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        locator.findElement();
        locator.findElement();

        verify(driver, times(1)).findElement(rootElementBy);
        verify(rootElement, times(1)).findElement(by);
    }

    @Test
    void cachedElementListShouldBeCached() throws Exception {
        final Field f = Block.class.getDeclaredField("cachedList");
        final By by = new ByIdOrName("cachedList");

        final WebElement element1 = mock(WebElement.class, "webElement1");
        final WebElement element2 = mock(WebElement.class, "webElement2");
        final List<WebElement> list = Arrays.asList(element1, element2);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElements(by))
                .thenReturn(list);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        locator.findElements();
        locator.findElements();

        verify(driver, times(1)).findElement(rootElementBy);
        verify(rootElement, times(1)).findElements(by);
    }

    @Test
    void shouldNotCacheNormalElement() throws Exception {
        final Field f = Block.class.getDeclaredField("first");
        final By by = new ByIdOrName("first");

        final WebElement element = mock(WebElement.class);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElement(by))
                .thenReturn(element);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        locator.findElement();
        locator.findElement();

        verify(driver, times(2)).findElement(rootElementBy);
        verify(rootElement, times(2)).findElement(by);
    }

    @Test
    void shouldNotCacheNormalElementList() throws Exception {
        final Field f = Block.class.getDeclaredField("list");
        final By by = new ByIdOrName("list");

        final WebElement element1 = mock(WebElement.class, "webElement1");
        final WebElement element2 = mock(WebElement.class, "webElement2");
        final List<WebElement> list = Arrays.asList(element1, element2);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElements(by))
                .thenReturn(list);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        locator.findElements();
        locator.findElements();

        verify(driver, times(2)).findElement(rootElementBy);
        verify(rootElement, times(2)).findElements(by);
    }

    @Test
    void shouldUseFindByAnnotationsWherePossible() throws Exception {
        final Field f = Block.class.getDeclaredField("byId");
        final By by = By.id("foo");

        final WebElement element = mock(WebElement.class);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElement(by))
                .thenReturn(element);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        final WebElement returnedElement = locator.findElement();

        assertEquals(element, returnedElement);
    }

    @Test
    void shouldUseFindAllByAnnotationsWherePossible() throws Exception {
        final Field f = Block.class.getDeclaredField("listById");
        final By by = By.id("foo");

        final WebElement element1 = mock(WebElement.class, "webElement1");
        final WebElement element2 = mock(WebElement.class, "webElement2");
        final List<WebElement> list = Arrays.asList(element1, element2);

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElements(by))
                .thenReturn(list);

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy))
                .thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        final List<WebElement> returnedList = locator.findElements();

        assertEquals(list, returnedList);
    }

    @Test
    void shouldNotMaskNoSuchElementExceptionIfThrownOnRootElement() throws Exception {
        final Field f = Block.class.getDeclaredField("byId");
        final By reBy = By.id("re");

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(reBy)).thenThrow(new NoSuchElementException("rootElement"));

        final ElementLocator locator = newLocator(driver, f);
        try {
            locator.findElement();
            fail("Should have allowed the exception to bubble up from the rootElement");
        } catch (final NoSuchElementException e) {
            // This is expected
        }
    }

    @Test
    void shouldNotMaskNoSuchElementExceptionIfThrownOnSubElement() throws Exception {
        final Field f = Block.class.getDeclaredField("byId");
        final By by = By.id("foo");

        final WebElement rootElement = mock(WebElement.class);
        when(rootElement.findElement(by)).thenThrow(new NoSuchElementException("subElement"));

        final WebDriver driver = mock(WebDriver.class);
        when(driver.findElement(rootElementBy)).thenReturn(rootElement);

        final ElementLocator locator = newLocator(driver, f);
        try {
            locator.findElement();
            fail("Should have allowed the exception to bubble up from the subElement");
        } catch (final NoSuchElementException e) {
            // This is expected
        }
    }

    @Test
    void shouldWorkWithCustomAnnotations() throws NoSuchFieldException {
        final WebDriver driver = mock(WebDriver.class);

        final AbstractAnnotations npeAnnotations = new AbstractAnnotations() {
            @Override
            public boolean isLookupCached() {
                return false;
            }

            @Override
            public By buildBy() {
                throw new NullPointerException();
            }
        };

        final Field rootElementField = Block.class.getDeclaredField("rootElement");

        assertThrows(
            NullPointerException.class,
            () -> new BlockingElementLocator(driver, rootElementField, npeAnnotations),
            "Custom annotation has not be called");

    }

    private static class Block {
        @SuppressWarnings("unused")
        @FindBy(how = How.ID, using = "re")
        private WebElement rootElement;

        @SuppressWarnings("unused")
        private WebElement first;

        @SuppressWarnings("unused")
        private List<WebElement> list;

        @SuppressWarnings("unused")
        @CacheLookup
        private WebElement cached;

        @SuppressWarnings("unused")
        @CacheLookup
        private List<WebElement> cachedList;

        @SuppressWarnings("unused")
        @FindBy(how = How.ID, using = "foo")
        private WebElement byId;

        @SuppressWarnings("unused")
        @FindBy(how = How.ID, using = "foo")
        private List<WebElement> listById;
    }
}