Setup:

Pre-requisite:
- Java 8 & above
- Maven
- Java IDE
- Cucumber plugin

Testcase Document --> AutomationTasks\TestCases.xlsx

UI Testcases:
	featureFile --> AutomationTasks\resources\FeatureFiles\UI_listAPIDetails.feature
	runner file --> AutomationTasks\src\test\java\com\test\runners\CucumberTestRunner.java

	Test executon: run CucumberTestRunner.java adding respective testcase tags

API Testcases:
	comment code within the hooks class as it is specific to UI Tests --> AutomationTasks\src\test\java\com\test\steps\Hooks.java
	featureFile --> AutomationTasks\resources\FeatureFiles\APITestCases.feature
	runner file --> AutomationTasks\src\test\java\com\test\runners\CucumberTestRunner.java

	Test executon: run CucumberTestRunner.java adding respective testcase tags

File Validation:
	comment code within the hooks class as it is specific to UI Tests --> AutomationTasks\src\test\java\com\test\steps\Hooks.java
	featureFile --> AutomationTasks\resources\FeatureFiles\ValidateOutputFile.feature
	runner file --> AutomationTasks\src\test\java\com\test\runners\CucumberTestRunner.java

	Test executon: run CucumberTestRunner.java adding respective testcase tags

Report:
Cucumber report generated under --> AutomationTasks\target

 