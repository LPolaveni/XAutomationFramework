package com.x.webAutomation.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.x.webAutomation.controllers.DriverClass;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import com.x.webAutomation.extentReport.ExtentFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.JUnitReportReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Listeners extends JUnitReportReporter implements ISuiteListener, ITestListener, IInvokedMethodListener, IRetryAnalyzer, IAnnotationTransformer {
	private static final Logger log = Log4jUtil.loadLogger(Listeners.class);
	private int retryCounter = 0;
	private final int retryLimit = 1;
	private int totalPassed = 0, totalFailed = 0, totalSkipped = 0, totalTcs = 0;
	private ExtentTest extLogger;




	@Override
	public void onStart(ISuite suite) {
		totalPassed = 0;
		totalTcs = 0;
		totalFailed = 0;
		totalSkipped = 0;
		log.info("=== Test Suite Started: " + suite.getName() + " ===");
	}

	@Override
	public void onFinish(ISuite suite) {

		log.info("Total test cases   :" + (totalPassed + totalSkipped + totalFailed));
		log.info("Total Passed: " + totalPassed);
		log.info("Total Failed: " + totalFailed);
		log.info("Total Skipped: " + totalSkipped);

		try {
			ExtentReports extentReport = ExtentFactory.getInstance();
			if (extentReport != null) {
				extentReport.flush();  // Flush once after suite ends
			}

			// Auto-open report in Chrome
			String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
			String testType = System.getProperty("testType", "DEFAULT").toUpperCase();
			Path reportPath = Paths.get(System.getProperty("user.dir"),
					"test-output", "ExtentReports", date, testType, "AutomationTestReport.html");

			if (Files.exists(reportPath)) {
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("win")) {
					Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome \"" + reportPath.toAbsolutePath() + "\""});
				} else if (os.contains("mac")) {
					Runtime.getRuntime().exec(new String[]{"open", "-a", "Google Chrome", reportPath.toString()});
				} else if (os.contains("nux")) {
					Runtime.getRuntime().exec(new String[]{"google-chrome", reportPath.toString()});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext test) {
		log.info("All Tests Execution Completed");
	}


	@Override
	public void onTestStart(ITestResult result) {
		try {
			totalTcs++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		totalPassed++;
		extLogger.log(Status.PASS, "Test case passed: " + result.getName());
		log.info("✅ Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("Test case failed: " + result.getName() + " --- Exception : " + result.getThrowable());
		extLogger.log(Status.FAIL, "Test case failed: " + result.getName() + " --- Exception : " + result.getThrowable());
		result.getThrowable().printStackTrace();
		totalFailed++;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extLogger.log(Status.SKIP, "Test case skipped: " + result.getName() + " --- Exception : " + result.getThrowable());
		result.getThrowable().printStackTrace();
		totalSkipped++;
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			WebDriver driver = null;
			String baseUrl = null;
			try {
				driver = DriverClass.createInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
			DriverManager.setDriver(driver);
			WebDriver driverInstance = DriverManager.getDriver();
			driverInstance.manage().deleteAllCookies();
			driverInstance.manage().window().maximize();

			// Load URL from env.properties
			Properties props = new Properties();
			try {
				props.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/webConfig/env.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			baseUrl = props.getProperty("url");

			// Configure browser
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(baseUrl);
			SetUpTest.scenarioName = method.getTestMethod().getMethodName();
			Reporter.getCurrentTestResult().getTestContext()
					.setAttribute("methodName" + Thread.currentThread().hashCode(), SetUpTest.scenarioName);
			SetUpTest.blnPortFlag = false;
			Reporter.getCurrentTestResult().getTestContext()
					.setAttribute("portFlag" + Thread.currentThread().hashCode(), SetUpTest.blnPortFlag);

		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			if (DriverManager.getDriver() != null) {
				log.info("Thread id = " + Thread.currentThread().getId());
				log.info("Hashcode of webDriver instance = " + DriverManager.getDriver().hashCode());
				if (DriverManager.getDriver() != null) {
					DriverManager.getDriver().quit();
					DriverManager.removeDriver();
				}
				this.extLogger = (ExtentTest) Reporter.getCurrentTestResult().getTestContext()
						.getAttribute("extLogger" + Thread.currentThread().hashCode());

			}
		}
	}

	@Override
	public boolean retry(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE && retryCounter < retryLimit) {
			retryCounter++;
			log.warn("🔁 Retrying test: " + result.getName() + " (" + retryCounter + "/" + retryLimit + ")");
			return true;
		}
		return false;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, java.lang.reflect.Constructor testConstructor, java.lang.reflect.Method testMethod) {
		annotation.setRetryAnalyzer(this.getClass());
	}
}
