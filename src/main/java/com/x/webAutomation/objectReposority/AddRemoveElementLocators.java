package com.x.webAutomation.objectReposority;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddRemoveElementLocators {

    @FindBy(xpath = "//div/div/button")
    public WebElement addElementButton;

    @FindBy(xpath = "//div/div/button[text()='Delete']")
    public WebElement deleteElementButton;




}
