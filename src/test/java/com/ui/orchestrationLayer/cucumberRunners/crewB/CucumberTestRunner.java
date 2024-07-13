package com.ui.orchestrationLayer.cucumberRunners.crewB;
import com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/ui/businessLayer/features/crewB",
        glue = {"com.ui.businessLayer.stepDefinitions.commonReusableSteps", "com.ui.businessLayer.stepDefinitions.crewB"},
        tags = "@Login2",
        plugin = {"pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber/report.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class CucumberTestRunner extends CustomCucumberTestNGTests {
}
