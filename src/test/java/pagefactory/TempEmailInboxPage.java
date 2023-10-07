package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TempEmailInboxPage extends BasePage {

    @FindBy(css = "#ifinbox")
    private WebElement inboxFrame;

    @FindBy(css = "#ifmail")
    private WebElement mailboxFrame;

    @FindBy(id = "refresh")
    private WebElement refreshButton;

    @FindBy(xpath = "//h3[text()[contains(.,'Total Estimated Monthly Cost')]]/parent::td/following-sibling::td/h3")
    private WebElement totalEstimatedMonthlyCost;

    public TempEmailInboxPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TempEmailInboxPage openPage() {
        return null;
    }

    private TempEmailInboxPage switchToFrame(WebElement frame) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame);
        return this;
    }

    public TempEmailInboxPage refreshUntilEmailReceived(String emailSubject) {
        switchToFrame(inboxFrame);
        List<WebElement> emailList;
        int maxRefreshCount = 100; //instead of using thread.sleep()
        while((emailList = driver.findElements(By.xpath(String.format("//div[contains(.,'%s')]", emailSubject)))).size() == 0 && maxRefreshCount-- > 0) {
            driver.switchTo().defaultContent();
            refreshButton.click();
            switchToFrame(inboxFrame);
        }
        emailList.get(0).click();

        return this;
    }

    public String getTotalEsimatedMonthlyCost() {
        switchToFrame(mailboxFrame);
        return totalEstimatedMonthlyCost.getText();
    }

}
