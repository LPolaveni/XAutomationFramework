package com.automation;

import com.x.webAutomation.common.Log4jUtil;
import com.x.webAutomation.controllers.DriverClass;
import com.x.webAutomation.scenarios.StatusCodeScenarios;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class StatusCodeTest extends DriverClass {

    Logger log = Log4jUtil.loadLogger(StatusCodeTest.class);
    StatusCodeScenarios statusCodeScenarios= new StatusCodeScenarios();

    public StatusCodeTest() throws IOException {
    }

    @Test
    public void verifyStatusCode200() throws Exception {
        statusCodeScenarios.statusCode200();
    }

    @Test
    public void verifyStatusCode301() throws Exception {
        statusCodeScenarios.statusCode301();

    }

    @Test
    public void verifyStatusCode404() throws Exception {
       statusCodeScenarios.statusCode404();

    }

    @Test
    public void verifyStatusCode500() throws Exception {
        statusCodeScenarios.statusCode500();
    }

}
