package com.automation;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class StandAloneTest extends SetUpTest {

    @Test
    public void welcomeTextTest() throws Exception {
        WebDriver driver = launchApplication();
        HomePageLocators homePageLocators = new HomePageLocators(driver);
        String expectedWelcomeText = "Welcome to the-internet";
        Assert.assertEquals(homePageLocators.getWelcomeElementText(), expectedWelcomeText);

    }
}
