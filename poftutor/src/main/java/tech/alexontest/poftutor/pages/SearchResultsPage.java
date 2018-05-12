package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;

@ImplementedBy(SearchResultsPageDesktop.class)
public interface SearchResultsPage extends ListingPage {
    String getPageUrl(String searchText);

    String getPageTitle();
}
