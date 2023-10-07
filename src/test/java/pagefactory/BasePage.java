package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;

public abstract class BasePage {
    private static final long TIMEOUT = 10;
    private static final long POLLING_TIME = 1;
    protected static WebDriver driver;
    protected static Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING_TIME))
                .ignoring(Exception.class);
        PageFactory.initElements(driver, this);
    }

    protected abstract BasePage openPage();

    public static void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public static void switchToNextTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        int currentTabIndex = tabs.indexOf(driver.getWindowHandle());
        if(currentTabIndex + 1 < tabs.size())
            driver.switchTo().window(tabs.get(currentTabIndex + 1));
        else
            driver.switchTo().window(tabs.get(0));
    }
}
