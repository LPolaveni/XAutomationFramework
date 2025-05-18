# 🧪 Selenium Test Automation Framework

This is a modular, data-driven automation testing framework built with Java, Selenium WebDriver, TestNG, Log4j, and ExtentReports.

---

## 📁 Project Structure

src/
├── main/
│ ├── java/
│ │ ├── com.x.webAutomation.controllers # Driver setup, Test base
│ │ ├── com.x.webAutomation.pages # Page Object classes
│ │ ├── com.x.webAutomation.utils # Common utilities (logging, waits, screenshots)
│ │ ├── com.x.webAutomation.reports # ExtentReport configuration
│ │ ├── com.x.webAutomation.common # Listener, logger, etc.
├── test/
│ ├── java/
│ │ ├── com.automation # Test classes using TestNG
│
resources/
├── webConfig/
│ ├── env.properties # Base URL and environment configs
├── commonConfig/
│ ├── log4j.properties # Log4j logging configuration


---

## 🚀 Getting Started

### 1. Prerequisites
- Java 17+
- Maven 3.6+
- IntelliJ IDEA / Eclipse
- Chrome browser installed

### 2. Setup
1. Clone the repository
2. Run `mvn clean install`
3. Update `env.properties` with your test environment URL

---

## 🧪 Running Tests

### From IntelliJ:
- Right-click on `testng.xml` → Run


 **To-Do**
 
Add Extent Report
Add Jenkins integration

Dockerize tests for CI

Add support for other browsers

Implement test data from Excel/JSON
