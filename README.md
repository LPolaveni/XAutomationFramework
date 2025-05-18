
# 🧪 Selenium Test Automation Framework

A modular, data-driven automation framework built with **Java**, **Selenium WebDriver**, **TestNG**, **Log4j**, and **ExtentReports**. Supports rich HTML reporting and is extensible for CI/CD integration.

---

## 📁 Structure Overview

```

src/
├── main/java/com/x/webAutomation/
│   ├── controllers     # WebDriver setup, test base
│   ├── pages           # Page Object Models
│   ├── utils           # Utilities (logs, waits, screenshots)
│   ├── reports         # ExtentReport configuration
│   ├── common          # Listeners, loggers
├── test/java/com/automation/  # TestNG test classes

resources/
├── webConfig/env.properties      # Test config (URL, browser)
├── commonConfig/log4j.properties # Logging configuration

````

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven 3.6+
- IntelliJ / Eclipse
- Google Chrome

### ⚙️ Setup

```bash
git clone <repo-url>
cd AutomatioFrameworks
mvn clean install
````

Edit `env.properties` to set your test environment and browser.

---

## 🧪 Running Tests

* **From IDE**: Right-click `testng.xml` → Run
* **From CLI**:

  ```bash
  mvn test -DsuiteXmlFile=testng.xml
  ```

---

## 📊 Reporting

* Uses **ExtentReports** (dark theme, system info, custom logo)
* Report auto-opens in Chrome after execution (Windows)
* Location:

  ```
  test-output/ExtentReports/<DATE>/DEFAULT/AutomationTestReport.html
  ```

---

## 📌 Roadmap

* ✅ ExtentReports integration with theme & system info
* ⏳ Jenkins CI/CD integration
* ⏳ Docker-based execution
* ⏳ Multi-browser support (Firefox, Edge)
* ⏳ Data-driven testing with Excel/JSON

---

## 🤝 Contributions

Open to improvements! Fork, raise issues, or submit PRs.

```

---

### ✅ Summary of Improvements

- Clear headings for each section
- Efficient project structure description
- Short and powerful roadmap
- Highlights features without over-explaining

