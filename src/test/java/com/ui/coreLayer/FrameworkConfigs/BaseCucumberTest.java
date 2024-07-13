package com.ui.coreLayer.FrameworkConfigs;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class BaseCucumberTest {

    protected WebDriver driver;
    protected ExtentTest testStep;
    protected SoftAssert softAssert;

    public BaseCucumberTest(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
        this.driver= driver;
        this.testStep = testStep;
        this.softAssert = softAssert;
    }


}
