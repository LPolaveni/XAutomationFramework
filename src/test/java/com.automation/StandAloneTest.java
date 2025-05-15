package com.automation;

import com.x.webAutomation.controllers.DriverClass;
import com.x.webAutomation.scenarios.HomePageScenarios;
import org.testng.annotations.Test;


public class StandAloneTest extends DriverClass {
    HomePageScenarios homePageScenarios = new HomePageScenarios();

    @Test
    public void welcomeTextTest() throws Exception {
        homePageScenarios.verifyWelcomeHomeText();
    }
}
