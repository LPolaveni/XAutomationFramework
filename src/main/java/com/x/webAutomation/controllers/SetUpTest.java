package com.x.webAutomation.controllers;


import com.x.webAutomation.common.Log4jUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Listeners(com.x.webAutomation.common.Listeners.class)
public class SetUpTest {
	protected static Logger log = Log4jUtil.loadLogger(SetUpTest.class);
	protected WebDriver driver;
	protected String baseUrl;

	@BeforeMethod
	public void setUp() throws IOException {

		// Create and set driver
		driver = DriverClass.createInstance();
		DriverManager.setDriver(driver);

		// Load URL from env.properties
		Properties props = new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/webConfig/env.properties"));
		baseUrl = props.getProperty("url");

		// Configure browser
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
			DriverManager.removeDriver();
		}
	}
}

