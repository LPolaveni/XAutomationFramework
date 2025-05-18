package com.x.webAutomation.controllers;


import com.x.webAutomation.common.Log4jUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Listeners(com.x.webAutomation.common.Listeners.class)
public class SetUpTest {
	protected static Logger log = Log4jUtil.loadLogger(SetUpTest.class);

	protected static String strEnv;
	protected static Properties props;
	public static String scenarioName;
	protected static String path;
	protected static String UTILS_FILE_PATH;
	protected static String reportPath;
	protected static String successimagespath;
	protected static String failureimagespath;
	protected String reasonPastDue;
	public static boolean blnPortFlag;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		log.info("***************** START TEST *****************");
		setPath();
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		log.info("***************** END TEST *****************");
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
		String env = null;
		if (env == null) {
			env = props.getProperty("defaultenv");
			log.info("Default Environment: " + props.getProperty("defaultenv"));
		} else {
			log.info("Environment: " + env);
		}
		return env;
	}
}

