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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Abstract super class to aid in the construction of blocks defined by a previously instantiated rootElement.
 * Be cautious using these blocks for testing AJAX applications, the rootElement is prone to
 * StaleElementReferenceExceptions if a DOM element above it in the tree is updated.
 */
public abstract class AbstractFoundBlock implements Block {

    private final WebElement rootElement;

    protected AbstractFoundBlock(final WebElement rootElement) {
        final ElementLocatorFactory locatorFactory = new BlockingElementLocatorFactory(rootElement);
        PageFactory.initElements(locatorFactory, this);
        this.rootElement = rootElement;
    }

    @Override
    public WebElement getRootElement() {
        return rootElement;
    }
}
