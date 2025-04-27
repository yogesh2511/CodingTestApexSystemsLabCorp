# QA Automation Coding Test - LabCorp Careers Automation

## 🛠 Project Overview
This project is designed to automate the browsing and validation of a LabCorp job listing using:
- **UI Automation:** Selenium WebDriver (Java)
- **API Automation:** REST Assured (Java)
- **Framework:** Cucumber BDD (for both UI and API tests)
- **Design Pattern:** Page Object Model (POM)
- **Reports:** HTML reports (ExtentReports and Cucumber Reports)
- **Logs:** Custom logging for test actions and errors

> ✅ The project is structured and ready to run during interview sessions.  
> ✅ All functionalities are built following best practices, proper wait handling, exception management, and clean code structure.

---

## 📚 Tech Stack
| Layer | Tech Used |
|:------|:----------|
| Programming Language | Java 17 |
| UI Automation | Selenium WebDriver |
| API Automation | Rest Assured |
| BDD Framework | Cucumber |
| Build Tool | Maven |
| Reporting | ExtentReports, Cucumber HTML Reports |
| Logging | Custom Logger Utility |
| Driver Management | WebDriverManager |
| Test Runner | Cucumber TestNG Runner |

---

## 🎯 Project Requirements Covered

- ✅ Open **www.labcorp.com** in Chrome.
- ✅ Navigate to **Careers** link.
- ✅ Search for a specific **job position** (example: *QA Test Automation Developer*).
- ✅ Browse and select a job listing.
- ✅ Assert the following details:
  - **Job Title**
  - **Job Location**
  - **Job ID**
  - **First sentence** of third paragraph under Description/Introduction
  - **Second bullet** under Management Support
  - **Third requirement** from Qualifications
  - **Suggested Automation Tool** familiarity containing "Selenium"
- ✅ Click **Apply Now** and re-validate Job Title, Job Location, and Job ID.
- ✅ Click **Return to Job Search** and handle child windows properly.

---

## 🧹 Project Highlights

- **Proper Waits:**  
  Implemented `ExplicitWait` and dynamic waits using WebDriverWait and ExpectedConditions.

- **Multiple Locator Strategies Used:**  
  - `By.id`
  - `By.xpath`
  - `By.cssSelector`

- **Window Handling:**  
  Smart handling of new browser tabs/windows during Apply Now and Return to Job Search actions with safety checks for WebDriver sessions.

- **Error Handling:**  
  Captures browser screenshots on failures, logs detailed error messages, and prevents crashes using session validation.

- **API Testing Layer:**  
  Included basic API hooks using RestAssured with request/response logging and context sharing.

---

## 🏛 Framework Structure

```
src/
├── main/
│   ├── java/
│   │   ├──api.client/
│   │   ├──api.models/
│   │   ├──api.services/
|   |   ├──hooks/
│   │   ├── pages/
│   │   ├── stepdefinitions/
│   │   ├── utilities/
│   │   └── runners/
│   └── resources/
│       ├── features/
│       ├── logs/
│       └── config/
└── test/
    ├── java/
    └── resources/
```

- **hooks:** Cucumber Hooks for UI (`@ui`) and API (`@api`) management
- **pages:** Page Object Model classes for each page interaction
- **stepdefinitions:** Step classes to bind Gherkin steps to methods
- **utilities:** Utility classes like DriverManager, Logger, WebElementHelper, JavaScript utilities, AliasManager
- **runners:** Cucumber Test Runner classes (with tag filters)
- **features:** Gherkin `.feature` files defining BDD scenarios

---

## 🚀 How to Run the Project

### Pre-requisites:
- Java 17 installed
- Maven installed
- Chrome browser installed
- Git configured

### Steps:
1. Clone this repository:
   ```bash
   git clone https://github.com/yogesh2511/CodingTestApexSystemsLabCorp.git
   cd CodingTestApexSystemsLabCorp
   ```

2. Clean and install Maven dependencies:
   ```bash
   mvn clean install
   ```

3. Run all scenarios:
   ```bash
   mvn test
   ```

4. View Reports:
   - **Extent Report:** `report/ExtentReport.html`
   - **Cucumber HTML Report:** `target/cucumber-reports.html`
   
---

## 📸 Additional Features
- **Screenshot on Failure**: Automatically attaches screenshots for failed UI scenarios.
- **Dynamic Driver Management**: No need to manually download chromedriver.
- **Custom Logs**: Clear info and error logs during execution for easy debugging.
- **Scenario Context Management**: Sharing data between steps cleanly using `ScenarioContextManager`.

---

## 🧠 Important Notes
- Please ensure **Chrome browser** version matches the Chromedriver version (automatically handled with Selenium WebDriverManager).
- Tests are designed to be **parallel-run ready** for future scalability.

---

## 📦 GitHub Upload Checklist

- [x] Code committed with proper folder structure
- [x] `.gitignore` added for target, reports, and local config files
- [x] ReadMe.md (this document) created
---

## 🤝 Credits
Developed and maintained by **Yogesh Solanki**  
Automation Enthusiast | Focused on Clean Test Automation Design
