package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlock;
import tech.alexontest.poftutor.pageblocks.TagCloudWidgetBlock;

import java.util.List;

@ImplementedBy(HomePageDesktop.class)
public interface HomePage extends Page {
    String getTitle();

    int getWidgetCount();

    List<PostSummaryBlock> getArticles();

    TagCloudWidgetBlock getTagCloudWidgetBlock();

    String getFooterText();

    List<String> getFooterLinks();
}
