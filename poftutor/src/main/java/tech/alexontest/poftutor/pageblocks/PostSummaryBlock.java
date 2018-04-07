package tech.alexontest.poftutor.pageblocks;

import tech.alexontest.poftutor.infrastructure.pagefactory.Block;

public interface PostSummaryBlock extends Block {
    String getPostTitle();

    String getAuthorName();

    String getPostDate();

    String getPostSummary();

    String getReadMore();
}
