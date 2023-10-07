package pagefactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudMainPage extends BasePage {
    private static final String PAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(.,'OK')]")
    private WebElement agreeButton;

    public GoogleCloudMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleCloudMainPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public SearchResultPage fillInSearchField(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchField)).clear();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public GoogleCloudMainPage agreePrivacyTerms() {
        try {
            wait.until(ExpectedConditions.visibilityOf(agreeButton)).click();
        } catch (Exception ignore) {
            //If there was no privacy term popup, just ignore it
        }
        return this;
    }
}
