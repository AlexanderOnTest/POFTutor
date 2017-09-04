package tech.alexontest.poftutor;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.alexontest.poftutor.infrastructure.AbstractSingleDriverTest;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverLaunchTest extends AbstractSingleDriverTest {
    private WebDriver driver;
    private String homePageURL = "https://alexanderontesting.com/";

    @Test
    public void urlIsCorrect() {
        setupHomepage();
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Test
    public void httpRewriteIsWorking() {
        driver = getDriver();
        driver.get("http://alexanderontesting.com/");
        assertThat(driver.getCurrentUrl())
                .isEqualToIgnoringCase(homePageURL);
    }

    @Test
    public void titleIsCorrect() {
        setupHomepage();

        //confirm the title text is correct
        assertThat(driver.findElement(By.cssSelector(".site-title")).getText())
                .isEqualToIgnoringCase("Alexander on Testing");
    }

    @Test
    public void pageContainsFiveWidgets() {
        setupHomepage();
        assertThat(driver.findElements(By.cssSelector(".widget")))
                .size()
                .isEqualTo(5);
    }

    private void setupHomepage() {
        driver = getDriver();
        driver.get(homePageURL);
    }
}