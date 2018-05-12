package tech.alexontest.poftutor.pageblocks;

import com.google.inject.ImplementedBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.Block;

@ImplementedBy(SearchWidgetBlockDesktop.class)
public interface SearchWidgetBlock extends Block {
    SearchWidgetBlock enterSearchText(String searchText);

    String getSearchBarText();

    void searchByEnter(String searchText);

    void submitSearchByButton();
}
