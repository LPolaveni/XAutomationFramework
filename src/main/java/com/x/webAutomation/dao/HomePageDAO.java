package com.x.webAutomation.dao;

import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.HomePageLocators;
import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class HomePageDAO extends SetUpTest {

    private WebDriver driverInstance;
    private HomePageLocators homePageLocators;
    private CommonUtilities commonUtilities;
    WebDriverWait explicitWait;
    private Properties useData;

    public HomePageDAO() throws Exception {
        this.driverInstance = DriverManager.getDriver();
        this.homePageLocators = PageFactory.initElements(this.driverInstance, HomePageLocators.class);
        this.commonUtilities = new CommonUtilities();
        explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(3));
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
