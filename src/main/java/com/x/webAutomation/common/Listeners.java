package com.x.webAutomation.common;


import com.x.webAutomation.controllers.DriverClass;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.JUnitReportReporter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;


public class Listeners extends JUnitReportReporter implements ISuiteListener, ITestListener, IInvokedMethodListener,IRetryAnalyzer, IAnnotationTransformer {
	public int totalpassed, totaltcs, totalskipped, totalfailed;
	Logger log = Log4jUtil.loadLogger(Listeners.class);
	HashMap<String,String> testMap=null;
	private int retryCounter = 0;
	private int retryLimit = 1;

	public void onStart(ISuite suite) {
		totalpassed = 0;
		totaltcs = 0;
		totalskipped = 0;
		totalfailed = 0;
	}

	public void onFinish(ISuite suite) {
		log.info("Total test cases   :" + (totalpassed + totalskipped + totalfailed));
		log.info("Total passed cases :" + totalpassed);
		log.info("Total failed cases :" + totalfailed);
		log.info("Total skipped cases:" + totalskipped);
	}

	/*
	 * For Test Listener related methods
	 */
	public void onStart(ITestContext test) {
	}

	public void onFinish(ITestContext test) {
		log.info("All Tests Execution Completed");
	}

	public synchronized void onTestStart(ITestResult test) {
		try {
			totaltcs++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void onTestSuccess(ITestResult test) {
		totalpassed++;
	}

	public synchronized void onTestFailure(ITestResult test) {
		log.info("Test case failed: " + test.getName() + " --- Exception : " + test.getThrowable());
		test.getThrowable().printStackTrace();
		totalfailed++;
	}

	public synchronized void onTestSkipped(ITestResult test) {
		log.info("Listener If test case skipped:" + test.getName() + " --- Exception : " + test.getThrowable());
		test.getThrowable().printStackTrace();
		totalskipped++;
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			WebDriver driver = null;
			try {
				driver = DriverClass.createInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
			DriverManager.setDriver(driver);
			WebDriver driverInstance = DriverManager.getDriver();
			driverInstance.manage().deleteAllCookies();
			driverInstance.manage().window().maximize();
			driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driverInstance.get(SetUpTest.strUrlVal);
			log.info("URL launched " + SetUpTest.strUrlVal);
			log.info("Launched Browser");
			}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			if (DriverManager.getDriver() != null) {
				log.info("Thread id = " + Thread.currentThread().getId());
				log.info("Hashcode of webDriver instance = " + DriverManager.getDriver().hashCode());
			}
		}
	}

	@Override
	public synchronized String getTestName(ITestResult result) {
		/*String customMethod = null;
		Object[] dataSet = result.getParameters();
		Map<Object, String> testData = new HashMap<Object, String>();
		testData = Utils.getStringAsMap(dataSet[0].toString().replaceAll("[{}]", ""), ",", "=");
		String testCaseKey = testData.get("TestCaseID") + "-" + result.getMethod().getMethodName();
		customMethod = testCaseKey *//* + " : " + testNameKey *//*;
		log.info("in get TestName and custom name is " + customMethod);
		return customMethod;*/
		return null;
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(Listeners.class);
	}


	@Override
	public boolean retry(ITestResult iTestResult) {
		return false;
	}
}

