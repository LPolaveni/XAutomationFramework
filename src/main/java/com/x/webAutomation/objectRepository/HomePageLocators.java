package com.x.webAutomation.objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {

    @FindBy(xpath = "//div/h1")
    public WebElement btnWelcomeText;

}
