package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
