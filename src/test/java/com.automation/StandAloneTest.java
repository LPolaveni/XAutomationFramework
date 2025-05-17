package com.automation;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.scenarios.HomePageScenarios;
import org.testng.annotations.Test;



public class StandAloneTest extends SetUpTest {
    HomePageScenarios homePageScenarios = new HomePageScenarios();

    @Test
    public void welcomeTextTest() throws Exception {
        homePageScenarios.verifyWelcomeHomeText();
    }
}
