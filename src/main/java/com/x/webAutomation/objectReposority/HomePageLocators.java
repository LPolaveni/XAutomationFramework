package com.x.webAutomation.objectReposority;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {

    @FindBy(xpath = "//div/h1")
    public WebElement welcomeTextHomePage;

    @FindBy(xpath = "//a[contains(text(),'Add/Remove Elements')]")
    public WebElement addRemoveElements;

    @FindBy(xpath = "//a[contains(text(),'Basic Auth')]")
    public WebElement basicAuth;

    @FindBy(xpath = "//a[contains(text(),'Status Codes')]")
    public WebElement statusCodes;

}
