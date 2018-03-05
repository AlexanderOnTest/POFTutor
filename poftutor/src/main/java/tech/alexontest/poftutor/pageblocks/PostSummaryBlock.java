package tech.alexontest.poftutor.pageblocks;

public interface PostSummaryBlock extends Block {
    String getPostTitle();

    String getAuthorName();

    String getPostDate();

    String getPostSummary();

    String getReadMore();
}
