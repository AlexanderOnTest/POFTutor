package tech.alexontest.poftutor.pageblocks;

import com.google.inject.ImplementedBy;
import org.apache.commons.lang3.tuple.Pair;
import tech.alexontest.poftutor.infrastructure.pagefactory.Block;

import java.util.List;

@ImplementedBy(MetaWidgetBlockDesktop.class)
public interface MetaWidgetBlock extends Block {
    String getTitle();

    List<Pair<String, String>> getLinks();
}
