package com.x.webAutomation.dao;

import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.HomePageLocators;
import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class HomePageDAO extends SetUpTest {

    private WebDriver driverInstance;
    private HomePageLocators homePageLocators;
    private CommonUtilities commonUtilities;
    WebDriverWait explicitWait;
    private Properties useData;
    private ExtentTest extLogger;
    private String scenario;

    public HomePageDAO(String scenario) throws Exception {
        this.driverInstance = DriverManager.getDriver();
        this.homePageLocators = PageFactory.initElements(this.driverInstance, HomePageLocators.class);
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
        commonUtilities.click(homePageLocators.addRemoveElements);
    }

    public String getWelcomeElementText() throws Exception {
        return commonUtilities.getElementText(homePageLocators.welcomeTextHomePage, "text returned");
    }

    public void goToStatusCodesPage() throws Exception {
        commonUtilities.click(homePageLocators.statusCodes);
    }
}
