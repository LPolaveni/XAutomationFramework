package com.x.webAutomation.controllers;

import com.x.webAutomation.objectReposority.HomePageLocators;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class SetUpTest {

    public HomePageLocators homePageLocators;
    public WebDriver driver;

    public static WebDriver createInstance() throws IOException {
        WebDriver driver = null;
        Properties envProps = new Properties();
        envProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/resources/webConfig/env.properties"));
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
            driver = new ChromeDriver();

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

    public WebDriver launchApplication() throws IOException {
       driver = createInstance();
        Properties envProps = new Properties();
        envProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/resources/webConfig/env.properties"));
       driver.get(envProps.getProperty("url"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
       driver.manage().window().maximize();
       homePageLocators = new HomePageLocators(driver);
       return driver;
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
