package com.x.webAutomation.scenarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.StatusCodeDAO;
import com.x.webAutomation.extentReport.ExtentFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusCodeScenarios extends SetUpTest{

    private StatusCodeDAO statusCodeDAO;


    public synchronized StatusCodeDAO getSTProps(String scenario) throws Exception {
        this.statusCodeDAO = new StatusCodeDAO(scenario);
        return statusCodeDAO;
    }

    public synchronized void setExtentlog(String scenario) {
        try {
            ExtentReports extentReport = ExtentFactory.getInstance(); // shared instance
            ExtentTest extlogger = extentReport.createTest(scenario);
            extlogger.assignCategory((String) Reporter.getCurrentTestResult().getTestContext()
                    .getAttribute("methodName" + Thread.currentThread().hashCode()));
            Reporter.getCurrentTestResult().getTestContext()
                    .setAttribute("extLogger" + Thread.currentThread().hashCode(), extlogger);
            Reporter.getCurrentTestResult().getTestContext()
                    .setAttribute("com/x/webAutomation/extentReport", extentReport); // Set shared instance
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void statusCode200() throws Exception {
        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.statusCodeDAO = getSTProps(scenario);

       // this.statusCodeDAO = getSTProps();
        statusCodeDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode200();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("200"));
    }

    public void statusCode301() throws Exception {
        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.statusCodeDAO = getSTProps(scenario);

        // this.statusCodeDAO = getSTProps();
        statusCodeDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode301();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("301"));
    }

    public void statusCode404() throws Exception {
        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.statusCodeDAO = getSTProps(scenario);

        // this.statusCodeDAO = getSTProps();
        statusCodeDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode404();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("404"));
    }

    public void statusCode500() throws Exception {
        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.statusCodeDAO = getSTProps(scenario);

        //this.statusCodeDAO = getSTProps();
        statusCodeDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode500();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("knkj"));
    }
}
