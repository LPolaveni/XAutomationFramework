package com.x.webAutomation.common;

import com.x.webAutomation.controllers.DriverManager;
import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.JUnitReportReporter;

public class Listeners extends JUnitReportReporter implements ISuiteListener, ITestListener, IInvokedMethodListener, IRetryAnalyzer, IAnnotationTransformer {
	private static final Logger log = Log4jUtil.loadLogger(Listeners.class);
	private int retryCounter = 0;
	private final int retryLimit = 1;
	private int totalPassed = 0, totalFailed = 0, totalSkipped = 0, totalTcs = 0;;


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
		log.info("✅ Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		totalFailed++;
		log.info("Test case failed: " + result.getName() + " --- Exception : " + result.getThrowable());
		result.getThrowable().printStackTrace();
		totalFailed++;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("Listener If test case skipped:" + result.getName() + " --- Exception : " + result.getThrowable());
		result.getThrowable().printStackTrace();
		totalSkipped++;
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			log.info("--- Before Method: " + method.getTestMethod().getMethodName() + " ---");
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
