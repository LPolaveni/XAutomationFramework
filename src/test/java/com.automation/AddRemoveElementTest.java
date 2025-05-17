package com.automation;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.scenarios.AddRemoveElementScenarios;
import org.testng.annotations.Test;


public class AddRemoveElementTest extends SetUpTest {
    AddRemoveElementScenarios addRemoveElementScenarios = new AddRemoveElementScenarios();

    @Test
    public void verifyAddRemoveElementTest() throws Exception {
        addRemoveElementScenarios.addRemoveElementCheck();
    }
}
