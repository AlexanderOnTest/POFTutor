package tech.alexontest.poftutor.steps;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import org.openqa.selenium.NoSuchElementException;
import tech.alexontest.poftutor.pageblocks.ArchivesWidgetBlock;
import tech.alexontest.poftutor.pageblocks.CategoriesWidgetBlock;
import tech.alexontest.poftutor.pageblocks.SearchWidgetBlock;
import tech.alexontest.poftutor.pageblocks.TagCloudWidgetBlock;
import tech.alexontest.poftutor.pages.SearchResultsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static tech.alexontest.poftutor.infrastructure.HttpTools.assertLinkIsNotBroken;

public class WidgetSteps {
    private final SearchWidgetBlock searchWidgetBlock;

    private final TagCloudWidgetBlock tagCloudWidgetBlock;

    private final CategoriesWidgetBlock categoriesWidgetBlock;

    private final ArchivesWidgetBlock archivesWidgetBlock;

    private final SearchResultsPage searchResultsPage;

    private final SearchResultsSteps searchResultsSteps;

    @Inject
    public WidgetSteps(final SearchWidgetBlock searchWidgetBlock,
                       final TagCloudWidgetBlock tagCloudWidgetBlock,
                       final CategoriesWidgetBlock categoriesWidgetBlock,
                       final ArchivesWidgetBlock archivesWidgetBlock,
                       final SearchResultsPage searchResultsPage,
                       final SearchResultsSteps searchResultsSteps) {
        this.searchWidgetBlock = searchWidgetBlock;
        this.tagCloudWidgetBlock = tagCloudWidgetBlock;
        this.categoriesWidgetBlock = categoriesWidgetBlock;
        this.archivesWidgetBlock = archivesWidgetBlock;
        this.searchResultsPage = searchResultsPage;
        this.searchResultsSteps = searchResultsSteps;
    }

    public WidgetSteps verifyYouCanEnterSearchText(final String searchText) {
        searchWidgetBlock.enterSearchText(searchText);
        assertThat(searchWidgetBlock.getSearchBarText())
                .as("Search Bar Text was not as expected")
                .isEqualTo(searchText);
        return this;
    }

    /**
     * Submit search for 'searchText' using the search button (magnifying glass icon).
     * This works regardless of if the searchText is found on the site.
     * @param searchText text to search for.
     * @return a self reference
     */
    public SearchResultsSteps search(final String searchText) {
        searchWidgetBlock.enterSearchText(searchText)
                .submitSearchByButton();
        return searchResultsSteps;
    }

    /**
     * Submit search for 'searchText' using the enter key.
     * NOTE - this will throw a NoSuchElementException if the searchText does not appear on the site.
     * @param searchText text to search for.
     * @return a self reference
     */
    public SearchResultsSteps searchByEnter(final String searchText) {
        searchWidgetBlock.searchByEnter(searchText);
        await(String.format("Search results failed to load for %s", searchText))
                .atMost(5, TimeUnit.SECONDS)
                .ignoreException(NoSuchElementException.class)
                .untilAsserted(() -> assertThat(searchResultsPage.getPageTitle())
                        .contains(searchText));
        return searchResultsSteps;
    }

    public WidgetSteps assertTagCloudTitle() {
        assertThat(tagCloudWidgetBlock.getTitle())
                .as("Tag Could Title is correct")
                .isEqualToIgnoringCase("Tags");
        return this;
    }

    public WidgetSteps assertMostProminentTags() {
        final List<String> mostImportantTags = ImmutableList.of(
                "Automated Testing",
                "WebDriver",
                "Selenium",
                "Selenium WebDriver",
                "JUnit5",
                "JUnit 5",
                "Java");
        assertThat(mostImportantTags)
                .as("The most important Tags are prominent")
                .isSubsetOf(tagCloudWidgetBlock.getProminentTags());
        return this;
    }

    public WidgetSteps verifyCategoriesWidgetLayout() {
        final List<String> expectedCategories = ImmutableList.of(
                "DevOps",
                "PageFactory Tutorial",
                "Software Development Life Cycle",
                "Technology Updates",
                "WordPress Implementation"
        );

        assertThat(categoriesWidgetBlock.getTitle())
                .as("Widget title is incorrect")
                .isEqualToIgnoringCase("Categories");

        assertThat(expectedCategories)
                .as("The most important Tags are prominent")
                .isSubsetOf(categoriesWidgetBlock.getCategories());
        return this;
    }

    public WidgetSteps verifyArchivesWidgetLayout() {

        assertThat(archivesWidgetBlock.getTitle())
                .as("Widget title is incorrect")
                .isEqualToIgnoringCase("Archives");

        assertThat(archivesWidgetBlock.getMonths().size())
                .as("Some months tags appear")
                .isGreaterThanOrEqualTo(9);
        return this;
    }

    public WidgetSteps verifyArchivesLinksAreValid() {
        //This link checking is intrinsically slow. Parallelism here halves the time taken.
        archivesWidgetBlock.getMonths().parallelStream()
                .forEach(p -> assertLinkIsNotBroken(p.getRight()));
        return this;
    }
}
