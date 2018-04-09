package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;

@ImplementedBy(NoSearchResultsPageDesktop.class)
public interface NoSearchResultsPage extends ListingPage, SearchResultsPage {

    String getMainSearchFieldText();
}
