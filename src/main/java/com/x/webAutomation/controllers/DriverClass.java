package com.x.webAutomation.controllers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverClass {

    public static WebDriver createInstance() throws IOException {
        WebDriver driver = null;
        Properties envProps = new Properties();
        envProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/webConfig/env.properties"));
        String strBrowserVal = envProps.getProperty("browser");
        if (strBrowserVal.toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            return driver;
        }
        if (strBrowserVal.toLowerCase().contains("internet")) {
            WebDriverManager.edgedriver().setup();
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (strBrowserVal.toLowerCase().contains("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-infobars"); // Corrected argument
            options.addArguments("--disable-backgrounding-occluded-windows");
            options.addArguments("--disable-notifications");
            options.setCapability("unhandledPromptBehavior", "ignore");
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            return driver;
        }
        return driver;
    }

}
