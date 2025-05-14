package com.x.automation.web;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import scenarios.HomePageScenarios;

import java.util.Map;

public class HomePageTest {
    Logger log = com.x.webAutomation.common.Log4jUtil.loadLogger(HomePageTest.class);
    HomePageScenarios homePageScenarios = new HomePageScenarios();

    @Test(groups = { "Regression", "Smoke"})
    public void verifyCpAppointmentCreationPayOnlineGuestUserInDC(Map<Object, String> testDataVal) throws Exception {
        homePageScenarios.getHomePageWelcomeText(testDataVal);
    }
}
