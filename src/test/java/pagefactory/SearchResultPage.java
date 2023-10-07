package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected SearchResultPage openPage() {
        return null;
    }

    public SearchResultPage openSearchResult(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(String.format("//div[@class='gs-title']/a[contains(.,'%s')]", text))))).click();
        return this;
    }
}
