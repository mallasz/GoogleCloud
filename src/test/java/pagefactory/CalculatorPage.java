package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorPage extends BasePage{
    @FindBy(css = "#cloud-site > devsite-iframe > iframe")
    private WebElement outerFrame;

    @FindBy(css = "#myFrame")
    private WebElement innerFrame;

    @FindBy(id = "input_98")
    private WebElement numberOfInstancesField;

    @FindBy(id = "input_99")
    private WebElement instancesForField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_90']//div[@class='md-text']")
    private WebElement operatingSystemField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_91']//div[normalize-space(text())='Regular']")
    private WebElement provisioningModelField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_92']//div[normalize-space(text())='General purpose']")
    private WebElement machineFamilyField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_93']")
    private WebElement seriesField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_94']//div")
    private WebElement machineTypeField;

    @FindBy(xpath = "//div[@class='md-label' and contains(text(),'Add GPUs.')]/preceding-sibling::div[contains(@class,'md-container md-ink-ripple')]")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_504']//div[@class='md-text ng-binding']")
    private WebElement numberOfGPUsField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_463']//div[@class='md-text ng-binding']")
    private WebElement localSSDField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_96']//div[@class='md-text ng-binding']")
    private WebElement datacenterLocationField;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_97']//div[@class='md-text']")
    private WebElement committedUsageField;

    @FindBy(xpath = "//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//button[@id='Email Estimate']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//b[contains(.,'Total Estimated Cost')]")
    private WebElement calculatorResult;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(.,'Send Email')]")
    private WebElement sendEmailButton;

    private static final Pattern TOTAL_ESTIMATED_COST_PATTERN = Pattern.compile("Total Estimated Cost:\s+(.*)\s+per");

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CalculatorPage openPage() {
        return null;
    }

    public CalculatorPage switchFrames() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerFrame));
        return this;
    }

    public CalculatorPage selectCalculatorType(String buttonText) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-pagination-wrapper//span[contains(.,'%s')]", buttonText))))).click();
        return this;
    }

    public CalculatorPage setNumberOfInstances(String value) {
        wait.until(ExpectedConditions.visibilityOf(numberOfInstancesField)).sendKeys(value);
        return this;
    }

    public CalculatorPage clearInstancesForField() {
        wait.until(ExpectedConditions.visibilityOf(instancesForField)).clear();
        return this;
    }

    public CalculatorPage selectOperatingSystem(String value) {
        wait.until(ExpectedConditions.visibilityOf(operatingSystemField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_100']/div[normalize-space(text())='%s']", value))))).click();
        return this;
    }

    public CalculatorPage setProvisioningModel(String value) {
        wait.until(ExpectedConditions.visibilityOf(provisioningModelField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_113']//div[normalize-space(text())='%s']", value))))).click();
        return this;
    }

    public CalculatorPage setMachineFamily(String value) {
        wait.until(ExpectedConditions.visibilityOf(machineFamilyField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_117']//div[normalize-space(text())='%s']", value))))).click();
        return this;
    }

    public CalculatorPage setSeries(String value) {
        wait.until(ExpectedConditions.visibilityOf(seriesField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_220']/div[text()[contains(.,'%s')]]", value))))).click();
        return this;
    }

    public CalculatorPage setMachineType(String value) {
        wait.until(ExpectedConditions.visibilityOf(machineTypeField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//*[@id='select_option_469']/div[@class='md-text ng-binding' and normalize-space(text())='%s']", value))))).click();
        return this;
    }

    public CalculatorPage addGPUs(String type, String count) {
        wait.until(ExpectedConditions.visibilityOf(addGPUsCheckbox)).click();

        wait.until(ExpectedConditions.visibilityOf(gpuTypeField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//div[@class='md-text ng-binding' and contains(text(),'%s')]", type))))).click();

        wait.until(ExpectedConditions.visibilityOf(numberOfGPUsField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_515']//div[contains(text(),'%s')]", count))))).click();
        return this;
    }

    public CalculatorPage setLocalSSD(String value) {
        wait.until(ExpectedConditions.visibilityOf(localSSDField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option/div[contains(text(),'%s')]", value))))).click();
        return this;
    }

    public CalculatorPage setDatacenterLocation(String value) {
        wait.until(ExpectedConditions.visibilityOf(datacenterLocationField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_263']//div[contains(text(),'%s')]", value))))).click();
        return this;
    }

    public CalculatorPage setCommittedUsage(String value) {
        wait.until(ExpectedConditions.visibilityOf(committedUsageField)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format("//md-option[@id='select_option_136']//div[contains(text(),'%s')]", value))))).click();
        return this;
    }

    public CalculatorPage clickAddToEstimateButton() {
        wait.until(ExpectedConditions.visibilityOf(addToEstimateButton)).click();
        return this;
    }

    public CalculatorPage clickEmailEstimateButton() {
        wait.until(ExpectedConditions.visibilityOf(emailEstimateButton)).click();
        return this;
    }

    public String getCalculatorResult() {
        String result = wait.until(ExpectedConditions.visibilityOf(calculatorResult)).getText();
        Matcher m = TOTAL_ESTIMATED_COST_PATTERN.matcher(result);
        return m.find() ? m.group(1) : null;
    }

    public CalculatorPage setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        return this;
    }

    public CalculatorPage clickSendEmailButton() {
        wait.until(ExpectedConditions.visibilityOf(sendEmailButton)).click();
        return this;
    }
}
