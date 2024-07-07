package com.ui.orchestrationLayer.cucumberRunners.crewA;
import com.ui.coreLayer.CommonUtilities.CustomCucumberTestNGTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/ui/businessLayer/features/crewA/Sample.feature",
        glue = "com.ui.businessLayer.stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class CucumberTestRunner extends CustomCucumberTestNGTests {
}
