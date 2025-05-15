package com.x.webAutomation.objectReposority;

import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusCodesPageLocators extends CommonUtilities {
    WebDriver driver;
    public WebDriverWait explicitWait;

    public StatusCodesPageLocators(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='200']")
    WebElement statusCode200;

    @FindBy(xpath = "//a[text()='301']")
    WebElement statusCode301;

    @FindBy(xpath = "//a[text()='404']")
    WebElement statusCode404;

    @FindBy(xpath = "//a[text()='500']")
    WebElement statusCode500;

    @FindBy(xpath = "//p")
    WebElement statusCodeReturn;


    public void clickOnStatusCode200() throws Exception {
        click(statusCode200);
    }

    public void clickOnStatusCode301() throws Exception {
        click(statusCode301);
    }

    public void clickOnStatusCode404() throws Exception {
        click(statusCode404);
    }

    public void clickOnStatusCode500() throws Exception {
        click(statusCode500);
    }

    public String getStatusCode() throws Exception {
        return getElementText(statusCodeReturn, "Status returned from text");
    }





}
