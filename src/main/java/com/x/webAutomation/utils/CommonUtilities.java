package com.x.webAutomation.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.x.webAutomation.common.Log4jUtil;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class CommonUtilities extends SetUpTest {

    protected WebDriver driverInstance = DriverManager.getDriver();
    Logger log = Log4jUtil.loadLogger(CommonUtilities.class);
    private String scenario;
    private ExtentTest extLogger;

    public CommonUtilities(String scenario) {
        this.scenario = scenario;
        this.extLogger = (ExtentTest) Reporter.getCurrentTestResult().getTestContext()
                .getAttribute("extLogger" + Thread.currentThread().hashCode());
    }

    public WebDriverWait driverWait() {
        WebDriverWait explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(3));
        return explicitWait;
    }

    public boolean click(WebElement locator) throws Exception {
        boolean blnVal = false;
        try {
            driverWait().until(ExpectedConditions.elementToBeClickable(locator));
            if (locator.isDisplayed()) {
                locator.click();
                log.info("Clicked on element: " + locator);
                blnVal = true;
            }

        } catch (Exception e) {
            log.error("Unable to click on element: " + locator);
            extLogger.log(Status.FAIL, "Unable to click on element: " + locator);
            e.printStackTrace();
            assertTrue(blnVal);
            throw (e);
        }
        return blnVal;
    }

    public String getElementText(WebElement locator, String message) throws Exception {
        String strRetVal = null;
        try {
            driverWait().until(ExpectedConditions.elementToBeClickable(locator));
            if (locator.isDisplayed()) {
                strRetVal = locator.getText();
                log.info("Successfully captured text from " + message + ": " + strRetVal);
            }

        } catch (Exception e) {
            log.error("Unable to captured the text :" + locator);
            extLogger.log(Status.FAIL, "Unable to captured the text :" + locator);
            e.printStackTrace();
            assertTrue(false);
            throw (e);
        }
        return strRetVal;
    }
}
