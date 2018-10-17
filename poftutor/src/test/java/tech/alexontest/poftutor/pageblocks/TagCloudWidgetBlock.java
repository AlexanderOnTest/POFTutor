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

package tech.alexontest.poftutor.pageblocks;

import com.google.inject.ImplementedBy;
import org.apache.commons.lang3.tuple.Pair;
import tech.alexanderontest.guicefactory.infrastructure.pagefactory.Block;

import java.util.List;

@ImplementedBy(TagCloudWidgetBlockDesktop.class)
public interface TagCloudWidgetBlock extends Block {
    String getTitle();

    List<Pair<String, String>> getTags();

    List<String> getProminentTags();
}
