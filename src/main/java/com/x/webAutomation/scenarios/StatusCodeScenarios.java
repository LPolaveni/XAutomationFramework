package com.x.webAutomation.scenarios;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.HomePageDAO;
import com.x.webAutomation.dao.StatusCodeDAO;
import org.testng.Assert;

public class StatusCodeScenarios extends SetUpTest {

    private StatusCodeDAO statusCodeDAO;
    private HomePageDAO homePageDAO;

    public synchronized StatusCodeDAO getSTProps() throws Exception {
        this.statusCodeDAO = new StatusCodeDAO();
        return statusCodeDAO;
    }

    public void statusCode200() throws Exception {
        this.statusCodeDAO = getSTProps();
        homePageDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode200();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("200"));
    }

    public void statusCode301() throws Exception {
        this.statusCodeDAO = getSTProps();
        homePageDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode301();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("301"));
    }

    public void statusCode404() throws Exception {
        this.statusCodeDAO = getSTProps();
        homePageDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode404();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("404"));
    }

    public void statusCode500() throws Exception {
        this.statusCodeDAO = getSTProps();
        homePageDAO.goToStatusCodesPage();
        statusCodeDAO.clickOnStatusCode500();
        String actual = statusCodeDAO.getStatusCode();
        Assert.assertTrue(actual.contains("knkj"));
    }
}
