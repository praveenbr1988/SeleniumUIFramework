package com.ui.orchestrationLayer.cucumberRunners.crewA;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/ui/businessLayer/features/crewA/login.feature",
        glue = "com.ui.businessLayer.stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
