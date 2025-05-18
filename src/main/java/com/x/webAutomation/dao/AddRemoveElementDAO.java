package com.x.webAutomation.dao;

import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.AddRemoveElementLocators;
import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AddRemoveElementDAO extends SetUpTest{

    private WebDriver driverInstance;
    private AddRemoveElementLocators addRemoveElementLocators;
    private CommonUtilities commonUtilities;
    WebDriverWait explicitWait;
    private Properties useData;
    private ExtentTest extLogger;
    private String scenario;

    public AddRemoveElementDAO(String scenario) throws IOException {
        this.driverInstance = DriverManager.getDriver();
        this.addRemoveElementLocators = PageFactory.initElements(this.driverInstance, AddRemoveElementLocators.class);
        explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(3));
        this.commonUtilities = new CommonUtilities(scenario);
        this.scenario = scenario;
        this.extLogger = (ExtentTest) Reporter.getCurrentTestResult().getTestContext()
                .getAttribute("extLogger" + Thread.currentThread().hashCode());

        useData = new Properties();
        useData.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/webConfig/utils.properties"));
    }

    public void goTOAddRemoveElementPage() throws Exception {
        commonUtilities.click(addRemoveElementLocators.addRemoveElements);
    }

    public void addNewElement() throws Exception {
        commonUtilities.click(addRemoveElementLocators.addElementButton);
    }

    public String getElementAdded() throws Exception {
        String elementAddedText = commonUtilities.getElementText(addRemoveElementLocators.deleteElementButton, "Adding Element");
        log.info(elementAddedText);
        return elementAddedText;
    }

    public void deleteElementButton() throws Exception {
        commonUtilities.click(addRemoveElementLocators.deleteElementButton);
    }

}
