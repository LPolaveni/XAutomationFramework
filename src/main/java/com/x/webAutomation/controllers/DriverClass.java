package com.x.webAutomation.controllers;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverClass {

    private static Object Log4jUtil;
    protected static  Logger log = com.x.webAutomation.common.Log4jUtil.loadLogger(DriverClass.class);


    public static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            log.info("User Directory is: " + System.getProperty("user.dir"));
            log.info("Chrome driver path is: " + System.getProperty("user.dir")
                    + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
            log.info("User Directory is: " + System.getProperty("user.dir"));
            log.info("Chrome driver path is: " + System.getProperty("user.dir")
                    + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("--disable-backgrounding-occluded-windows");
            options.addArguments("--disable-notifications");
            options.setCapability("unexpectedAlertBehaviour", UnexpectedAlertBehaviour.IGNORE);
            options.setCapability("applicationCacheEnabled", false);
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            return driver;
        }
        return driver;
    }
}

