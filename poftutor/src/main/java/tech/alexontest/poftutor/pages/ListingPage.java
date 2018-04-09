package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;
import tech.alexontest.poftutor.infrastructure.pagefactory.Page;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;

import java.util.List;

@ImplementedBy(ListingPageDesktop.class)
public interface ListingPage extends Page {
    String getSiteTitle();

    int getWidgetCount();

    List<PostSummaryBlock> getArticles();

    String getFooterText();

    List<String> getFooterLinks();
}
