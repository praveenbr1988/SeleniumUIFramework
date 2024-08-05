package com.ui.orchestrationLayer.cucumberRunners.crewA;
import com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/ui/businessLayer/features/crewA",
        glue = "com.ui.businessLayer.stepDefinitions",
        tags = "@Login",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber/report.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class CucumberTestRunner extends CustomCucumberTestNGTests {
}
