package com.x.webAutomation.controllers;


import com.x.webAutomation.common.Log4jUtil;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Properties;

public class SetUpTest {
	protected static Logger log = Log4jUtil.loadLogger(SetUpTest.class);
	protected static String suiteName;
	public static String strUrlVal;
	public static String strClassName;
	protected static String strEnv;
	public static String strFlow;
	protected static Properties props;
	public static String scenarioName;
	protected static String path;
	protected static String UTILS_FILE_PATH;
	protected static String reportPath;
	protected static String successimagespath;
	protected static String failureimagespath;
	protected String reasonPastDue;

	@BeforeMethod(alwaysRun = true)
	public void startOfTest(ITestContext context, Method method) throws Exception {
		log.info("***************** START TEST *****************");
		strUrlVal = getURL(strEnv, strFlow);
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult, ITestContext context) throws Exception {
		DriverManager.getDriver().quit();
	}
	
	

	@BeforeSuite(alwaysRun = true)
	public void launchApplication(ITestContext ctx) throws Exception {
		setPath();
		suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		try {
			strClassName = this.getClass().getSimpleName();
			strFlow = strClassName;
			strFlow = this.getClass().getSimpleName().replaceAll("Test.*", "").trim().toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void closeTheDBConnection() throws SQLException {
		//db.closeDBConnection();
	}

	public String getURL(String env, String strFlow) throws FileNotFoundException, IOException {
		Properties envProps = new Properties();
		if (env.equalsIgnoreCase("Prod")) {
			envProps.load(new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/webConfig/env.properties"));
			strUrlVal = envProps.getProperty("url");
		}
		else {
			strUrlVal = "https://the-internet.herokuapp.com/";
		}
		return strUrlVal;
	}

	private void setPath() {
		{
			props = new Properties();
			path = System.getProperty("user.dir");
			try {
				strEnv = getEnv();
				UTILS_FILE_PATH = path + "/src/main/resources/webConfig/utils.properties";
				props.load(new FileInputStream(UTILS_FILE_PATH));
				reportPath = props.getProperty("reportPath");
				successimagespath = path + props.getProperty("successimagespath");
				failureimagespath = path + props.getProperty("failureimagespath");
				reasonPastDue = props.getProperty("reasonPastDue");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String getEnv() throws Exception {
		String env = System.getProperty("TestEnv").toLowerCase();
		if (env == null) {
			env = props.getProperty("defaultenv");
			log.info("Default Environment: " + props.getProperty("defaultenv"));
		} else {
			log.info("Environment: " + env);
		}
		return env;
	}
}
