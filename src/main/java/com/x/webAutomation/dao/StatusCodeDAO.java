package com.x.webAutomation.dao;

import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.StatusCodesPageLocators;
import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class StatusCodeDAO extends SetUpTest {

    private WebDriver driverInstance;
    private StatusCodesPageLocators statusCodesPageLocators;
    private CommonUtilities commonUtilities;
    WebDriverWait explicitWait;
    private Properties useData;
    private ExtentTest extLogger;
    private String scenario;

    public StatusCodeDAO(String scenario) throws IOException {
        this.driverInstance = DriverManager.getDriver();
        this.statusCodesPageLocators = PageFactory.initElements(this.driverInstance, StatusCodesPageLocators.class);
        explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(3));
        this.commonUtilities = new CommonUtilities(scenario);
        this.scenario = scenario;
        this.extLogger = (ExtentTest) Reporter.getCurrentTestResult().getTestContext()
                .getAttribute("extLogger" + Thread.currentThread().hashCode());
        useData = new Properties();
        useData.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/webConfig/utils.properties"));
    }


    public void clickOnStatusCode200() throws Exception {
        commonUtilities.click(statusCodesPageLocators.statusCode200);
        log.info("clicked on status 200");
    }

    public void goToStatusCodesPage() throws Exception {
        commonUtilities.click(statusCodesPageLocators.statusCodes);
    }

    public void clickOnStatusCode301() throws Exception {
        commonUtilities.click(statusCodesPageLocators.statusCode301);
        log.info("clicked on status 301");
    }

    public void clickOnStatusCode404() throws Exception {
        commonUtilities.click(statusCodesPageLocators.statusCode404);
        log.info("clicked on status 400");
    }

    public void clickOnStatusCode500() throws Exception {
        commonUtilities.click(statusCodesPageLocators.statusCode500);
        log.info("clicked on status 500");
    }

    public String getStatusCode() throws Exception {
        log.info(commonUtilities.getElementText(statusCodesPageLocators.statusCodeReturn,"logging text"));
        return commonUtilities.getElementText(statusCodesPageLocators.statusCodeReturn, "Status returned from text");
    }
}
