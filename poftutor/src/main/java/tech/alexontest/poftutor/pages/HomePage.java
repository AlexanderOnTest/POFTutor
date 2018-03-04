package tech.alexontest.poftutor.pages;

import com.google.inject.ImplementedBy;
import tech.alexontest.poftutor.pageblocks.PostSummaryBlockDesktop;
import tech.alexontest.poftutor.pageblocks.TagCloudWidgetBlock;

import java.util.List;

@ImplementedBy(HomePageDesktop.class)
public interface HomePage extends Page {
    String getTitle();

    int getWidgetCount();

    List<PostSummaryBlockDesktop> getArticles();

    TagCloudWidgetBlock getTagCloudWidgetBlock();

    String getFooterText();

    List<String> getFooterLinks();
}
