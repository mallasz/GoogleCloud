package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pagefactory.*;

import java.util.stream.Stream;


public class GoogleCloudPlatformTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("provideValues")
    public void calculatorTest(String searchText, String linkText, String buttonText, String numOfInstances, String operatingSystem,
                               String provisioningModel, String machineFamily, String series, String machineType, String gpuType,
                               String gpuCount, String ssd, String location, String committedUsage, String emailSubject) {
        new GoogleCloudMainPage(driver)
                .openPage()
                .agreePrivacyTerms()
                .fillInSearchField(searchText)
                .openSearchResult(linkText);

        String calculatedValue = new CalculatorPage(driver)
                .switchFrames()
                .selectCalculatorType(buttonText)
                .setNumberOfInstances(numOfInstances)
                .clearInstancesForField()
                .selectOperatingSystem(operatingSystem)
                .setProvisioningModel(provisioningModel)
                .setMachineFamily(machineFamily)
                .setSeries(series)
                .setMachineType(machineType)
                .addGPUs(gpuType, gpuCount)
                .setLocalSSD(ssd)
                .setDatacenterLocation(location)
                .setCommittedUsage(committedUsage)
                .clickAddToEstimateButton()
                .getCalculatorResult();

        BasePage.openNewTab();

        String emailAddress = new TempEmailMainPage(driver)
                .openPage()
                .acceptPrivacyTerms()
                .generateEmail()
                .getGeneratedEmailAddress();

        BasePage.switchToNextTab();

        new CalculatorPage(driver)
                .switchFrames()
                .clickEmailEstimateButton()
                .setEmail(emailAddress)
                .clickSendEmailButton();

        BasePage.switchToNextTab();

        String emailValue = new TempEmailMainPage(driver)
                .checkInbox()
                .refreshUntilEmailReceived(emailSubject)
                .getTotalEsimatedMonthlyCost();

        Assertions.assertEquals(calculatedValue, emailValue);
    }

    static Stream<Arguments> provideValues() {
        //there is no result for "Google Cloud Platform Pricing Calculator"
        return Stream.of(
                Arguments.of("Google Cloud Platform Pricing Calculator", "Google Cloud Pricing Calculator",
                        "Compute Engine", "4", "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)",
                        "Regular", "General purpose", "N1", "n1-standard-8 (vCPUs: 8, RAM: 30GB)", "NVIDIA Tesla V100", "1",
                        "2x375 GB", "Frankfurt (europe-west3)", "1 Year", "Google Cloud Price Estimate")
        );
    }
}
