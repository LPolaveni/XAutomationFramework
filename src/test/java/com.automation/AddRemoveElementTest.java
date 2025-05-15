package com.automation;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.objectReposority.AddRemoveElementLocators;
import com.x.webAutomation.objectReposority.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRemoveElementTest extends SetUpTest {

    @Test
    public void addRemoveElementTest() throws Exception {

        WebDriver driver = launchApplication();
        HomePageLocators homePageLocators = new HomePageLocators(driver);
        homePageLocators.goTOAddRemoveElementPage();

        AddRemoveElementLocators addRemoveElementLocators = new AddRemoveElementLocators(driver);
        addRemoveElementLocators.addNewElement();
        String actual = addRemoveElementLocators.getElementAdded();
        addRemoveElementLocators.deleteElementButton();
        Assert.assertEquals(actual, "Delete");

    }
}
