package com.x.webAutomation.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class ExtentFactory {

	private static ExtentReports extent;

	public static synchronized ExtentReports getInstance() throws IOException {
		if (extent == null) {
			// Build a shared report path (not per test method)
			String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
			String testType = System.getProperty("testType", "DEFAULT").toUpperCase();

			Path reportDir = Paths.get("test-output", "ExtentReports", date, testType);
			Files.createDirectories(reportDir); // Ensures path exists

			// Create a single ExtentSparkReporter
			Path reportFile = reportDir.resolve("AutomationTestReport.html");
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile.toString());

			// Load Extent config
			Path configPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "extent", "extent-config.xml");
			sparkReporter.loadXMLConfig(configPath.toFile());

			// Custom branding
			sparkReporter.config().setDocumentTitle("X - Automation Report");
			sparkReporter.config().setReportName("X - Regression Report");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().thumbnailForBase64(true);
			// Attach reporter
			Locale.setDefault(Locale.ENGLISH);
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Load environment details
			Properties props = new Properties();
			Path envPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "webConfig", "env.properties");
			props.load(Files.newInputStream(envPath));
			String browser = props.getProperty("browser", "Unknown");

			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Browser", browser);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.setSystemInfo("Execution Time", new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date()));
		}

		return extent;
	}
}
