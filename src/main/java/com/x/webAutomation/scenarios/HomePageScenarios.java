package com.x.webAutomation.scenarios;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.HomePageDAO;
import org.testng.Assert;

public class HomePageScenarios extends SetUpTest {
    private HomePageDAO homePageDAO;

    public synchronized HomePageDAO getSTProps() throws Exception {
        this.homePageDAO = new HomePageDAO();
        return homePageDAO;
    }

    public void verifyWelcomeHomeText() throws Exception {
        this.homePageDAO = getSTProps();
        String welcomeText = homePageDAO.getWelcomeElementText();
        log.info(welcomeText);
        Assert.assertEquals(welcomeText,"Welcome to the-internet");
    }

}
