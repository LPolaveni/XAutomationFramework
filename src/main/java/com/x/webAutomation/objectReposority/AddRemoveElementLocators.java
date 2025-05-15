package com.x.webAutomation.objectReposority;

import com.x.webAutomation.utils.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddRemoveElementLocators extends CommonUtilities {
    WebDriver driver;

    public AddRemoveElementLocators(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/div/button")
    WebElement addElementButton;

    @FindBy(xpath = "//div/div/button[text()='Delete']")
    WebElement deleteElementButton;

    public void addNewElement() throws Exception {
        click(addElementButton);
    }

    public String getElementAdded() throws Exception {
        String elementAddedText = getElementText(deleteElementButton, "Adding Element");
        return elementAddedText;
    }

    public void deleteElementButton() throws Exception {
        click(deleteElementButton);
    }




}
