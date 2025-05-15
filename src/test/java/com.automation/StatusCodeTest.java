package com.automation;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.StatusCodesPageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTest extends SetUpTest {

    @Test
    public void verifyStatusCode200() throws Exception {
        WebDriver driver = launchApplication();
        homePageLocators.gotToStatusCodesPage();
        StatusCodesPageLocators statusCodesPageLocators = new StatusCodesPageLocators(driver);
        statusCodesPageLocators.clickOnStatusCode200();
        String actual = statusCodesPageLocators.getStatusCode();
        Assert.assertTrue(actual.contains("200"));

    }

    @Test
    public void verifyStatusCode301() throws Exception {
        WebDriver driver = launchApplication();
        homePageLocators.gotToStatusCodesPage();
        StatusCodesPageLocators statusCodesPageLocators = new StatusCodesPageLocators(driver);
        statusCodesPageLocators.clickOnStatusCode301();
        String actual = statusCodesPageLocators.getStatusCode();
        Assert.assertTrue(actual.contains("301"));

    }

    @Test
    public void verifyStatusCode404() throws Exception {
        WebDriver driver = launchApplication();
        homePageLocators.gotToStatusCodesPage();
        StatusCodesPageLocators statusCodesPageLocators = new StatusCodesPageLocators(driver);
        statusCodesPageLocators.clickOnStatusCode404();
        String actual = statusCodesPageLocators.getStatusCode();
        Assert.assertTrue(actual.contains("404"));

    }

    @Test
    public void verifyStatusCode500() throws Exception {
        WebDriver driver = launchApplication();
        homePageLocators.gotToStatusCodesPage();
        StatusCodesPageLocators statusCodesPageLocators = new StatusCodesPageLocators(driver);
        statusCodesPageLocators.clickOnStatusCode500();
        String actual = statusCodesPageLocators.getStatusCode();
        Assert.assertTrue(actual.contains("500"));

    }

}
