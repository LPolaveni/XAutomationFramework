package com.x.webAutomation.scenarios;

import com.x.webAutomation.dao.AddRemoveElementDAO;
import org.testng.Assert;

public class AddRemoveElementScenarios {

    private AddRemoveElementDAO addRemoveElementDAO;


    public synchronized AddRemoveElementDAO getSTProps() throws Exception {
        this.addRemoveElementDAO = new AddRemoveElementDAO();
        return addRemoveElementDAO;
    }


    public void addRemoveElementCheck() throws Exception {
        this.addRemoveElementDAO = getSTProps();
        addRemoveElementDAO.goTOAddRemoveElementPage();
        addRemoveElementDAO.addNewElement();
        String actual = addRemoveElementDAO.getElementAdded();
        Assert.assertEquals(actual, "Delete");
        addRemoveElementDAO.deleteElementButton();
    }
}
