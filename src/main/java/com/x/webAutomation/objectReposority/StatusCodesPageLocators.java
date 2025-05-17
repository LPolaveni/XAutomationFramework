package com.x.webAutomation.objectReposority;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatusCodesPageLocators{

    @FindBy(xpath = "//a[text()='200']")
    public WebElement statusCode200;

    @FindBy(xpath = "//a[text()='301']")
    public WebElement statusCode301;

    @FindBy(xpath = "//a[text()='404']")
    public WebElement statusCode404;

    @FindBy(xpath = "//a[text()='500']")
    public WebElement statusCode500;

    @FindBy(xpath = "//p")
    public WebElement statusCodeReturn;

    @FindBy(xpath = "//a[contains(text(),'Status Codes')]")
    public WebElement statusCodes;
}
