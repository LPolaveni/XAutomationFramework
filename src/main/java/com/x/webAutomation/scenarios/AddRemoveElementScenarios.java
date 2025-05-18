package com.x.webAutomation.scenarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.AddRemoveElementDAO;
import com.x.webAutomation.extentReport.ExtentFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRemoveElementScenarios extends SetUpTest {

    private AddRemoveElementDAO addRemoveElementDAO;


    public synchronized AddRemoveElementDAO getSTProps(String scenario) throws Exception {
        this.addRemoveElementDAO = new AddRemoveElementDAO(scenario);
        return addRemoveElementDAO;
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

    public void addRemoveElementCheck() throws Exception {

        String scenario = "TC-";
        scenario =  Thread.currentThread().getStackTrace()[2].getMethodName() + " "
                + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        log.info("Scenario: " + scenario);
        setExtentlog(scenario);
        this.addRemoveElementDAO = getSTProps(scenario);

        //this.addRemoveElementDAO = getSTProps();
        addRemoveElementDAO.goTOAddRemoveElementPage();
        addRemoveElementDAO.addNewElement();
        String actual = addRemoveElementDAO.getElementAdded();
        Assert.assertEquals(actual, "Delete");
        addRemoveElementDAO.deleteElementButton();
    }
}
