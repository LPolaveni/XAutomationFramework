package com.x.webAutomation.scenarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.HomePageDAO;
import com.x.webAutomation.extentReport.ExtentFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageScenarios extends SetUpTest {
    private HomePageDAO homePageDAO;

    public synchronized HomePageDAO getSTProps(String scenario) throws Exception {
        this.homePageDAO = new HomePageDAO(scenario);
        return homePageDAO;
    }

    public synchronized void setExtentlog(String scenario) {
        try {
            ExtentFactory extentFactory = new ExtentFactory();
            ExtentReports extentReport = extentFactory.getInstance();
            Reporter.getCurrentTestResult().getTestContext()
                    .setAttribute("com/x/webAutomation/extentReport" + Thread.currentThread().hashCode(), extentReport);
            ExtentTest extlogger = extentReport.createTest(scenario.substring(0, scenario.length() - 19));
            extlogger.assignCategory((String) Reporter.getCurrentTestResult().getTestContext()
                    .getAttribute("methodName" + Thread.currentThread().hashCode()));
            Reporter.getCurrentTestResult().getTestContext()
                    .setAttribute("extLogger" + Thread.currentThread().hashCode(), extlogger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyWelcomeHomeText() throws Exception {
        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.homePageDAO = getSTProps(scenario);

        //this.homePageDAO = getSTProps();
        String welcomeText = homePageDAO.getWelcomeElementText();
        log.info(welcomeText);
        Assert.assertEquals(welcomeText,"Welcome to the-internet");
    }

}
