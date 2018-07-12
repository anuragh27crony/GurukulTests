# SeleniumTests for GurukulApp

## 1. Build/Run Tests
 *Requirements:*
  - JDK (1.8.*)
  - Maven 3.2.5 & Above
  - OS: Windows 7+
  - Browsers : Firefox(Default)/Chrome

*Run Following command in a CMD prompt*
```
mvn clean verify
```
*After completion of Tests, Execution Report is generated at*
```
${Repo.Dir}/target/cucumberReports
```

## 2. Functional Tests
#### Folder Structure
```
SeleniumTest/
+-- .gitignore
+-- BugReport.docx                                      # Contains Detailed descption of Bugs identified.
+-- MissingScenarios.docx                               # Contains descption of scenarios that couldn't be Automated.
+-- .gitignore
+-- pom.xml
+-- src
    +-- test/java
    ¦   +-- PageObjects                                  # Contains PageObject impementations of various pages.
    ¦   ¦   +-- Entities                                 # Contains PageObject impementations of Branch & Staff related pages.
    ¦   +-- POJO
    ¦       +-- Branch                                   # Basic definition of Branch Entity
    ¦       +-- Staff                                    # Basic definition of Staff Entity
    ¦   +-- stepdefs                                     # Contains All Step implementations
    ¦   +-- Utils
    ¦   ¦   +-- WebDriverUtils.java                      # Utils for Creating WebDriver Instances and FluentWait checks for WebElements
    ¦   +-- RunCukesTest.java                            # Main Test Runner file
    +-- resources
        +-- Drivers
        ¦   +-- chromedriver.exe                         # Driver for invoking chrome browser
        ¦   +-- geckodriver.exe                          # Driver for invoking Firefox browser
        +-- FunctionalTests                              # BDD Feature files describing scenarios along with steps
        
```
#### Libraries & Documents
- Functional Tests are written in BDD fashion using *Cucumber-JVM*.
- Please refer to *BugReport.docx* for bugs identified in Gurukul application.
- Please also refer to *MissingScenarios.docx* for scenarios that are missing from this automation suite. 