package com.x.webAutomation.scenarios;

import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.dao.AddRemoveElementDAO;
import com.x.webAutomation.dao.HomePageDAO;
import org.testng.Assert;

public class AddRemoveElementScenarios extends SetUpTest {

    private HomePageDAO homePageDAO;
    private AddRemoveElementDAO addRemoveElementDAO;



    public void addRemoveElementCheck() throws Exception {
        homePageDAO.goTOAddRemoveElementPage();
        addRemoveElementDAO.addNewElement();
        String actual = addRemoveElementDAO.getElementAdded();
        Assert.assertEquals(actual, "Delete");
        addRemoveElementDAO.deleteElementButton();
    }
}
