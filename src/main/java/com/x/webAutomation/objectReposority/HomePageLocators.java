package com.x.webAutomation.objectReposority;

import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageLocators extends CommonUtilities {

    WebDriver driver;
    public WebDriverWait explicitWait;

    public HomePageLocators(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/h1")
    WebElement welcomeTextHomePage;

    @FindBy(xpath = "//a[contains(text(),'Add/Remove Elements')]")
    WebElement addRemoveElements;

    @FindBy(xpath = "//a[contains(text(),'Basic Auth')]")
    WebElement basicAuth;

    @FindBy(xpath = "//a[contains(text(),'Status Codes')]")
    WebElement statusCodes;

    public void goTOAddRemoveElementPage() throws Exception {
        click(addRemoveElements);
    }

    public String getWelcomeElementText() throws Exception {
        return getElementText(welcomeTextHomePage, "text returned");
    }

    public void gotToStatusCodesPage() throws Exception {
        click(statusCodes);
    }

}
