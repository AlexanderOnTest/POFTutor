package tech.alexontest.poftutor.pageblocks;

import com.google.inject.ImplementedBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.Block;

import java.util.List;

@ImplementedBy(CategoriesWidgetBlockDesktop.class)
public interface CategoriesWidgetBlock extends Block {
    String getTitle();

    List<String> getCategories();
}
