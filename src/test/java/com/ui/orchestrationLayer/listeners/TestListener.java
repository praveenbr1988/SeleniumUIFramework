package com.ui.orchestrationLayer.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.ui.coreLayer.CommonUtilities.ScreenShotUtil;
import com.ui.coreLayer.FrameworkConfigs.ExtentManager;
import com.ui.coreLayer.FrameworkConfigs.ExtentReportsNG;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.orchestrationLayer.Generics.TestMembersFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests.setTestListener;



public class TestListener implements ITestListener {

    protected static final Logger logger = LoggerUtil.getLogger(TestListener.class);


    @Override
    public void onStart(ITestContext context) {
        System.out.println("TestNG Annotation-Before TEst  Suite: " + context.getName());
        logger.info("TESTNG Annotation-Before Test Suite ");
        TestMembersFactory.setReport(ExtentReportsNG.setupExtentReport());
        setTestListener(context);

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TESTNG- Before Each Scenario/TestCase: "+ result.getName());
        logger.info("TESTNG- Before Each Scenario/TestCase: "+ result.getName());

        ExtentManager.getInstance().setExtent(TestMembersFactory.getReport().createTest(result.getMethod().getMethodName()));
        TestMembersFactory.setTestStep(ExtentManager.getInstance().getExtent());

        TestMembersFactory.setSoftAssert();
        //setTestParameters(result);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        TestMembersFactory.getTestStep().log(Status.PASS, result.getMethod().getMethodName()+ " - Test Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        TestMembersFactory.getTestStep().log(Status.FAIL, result.getMethod().getMethodName()+ " - Test Failed..."+result.getThrowable());
        TestMembersFactory.getTestStep().info("Refer Screenshot for Failure").addScreenCaptureFromBase64String(ScreenShotUtil.getBase64Screenshot());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        TestMembersFactory.getTestStep().log(Status.SKIP, result.getMethod().getMethodName()+ " - Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("TestNG Annotation-Test Suite finished: " + context.getName());
        logger.info("TESTNG Annotation-After Test Suite completed");
        TestMembersFactory.getTestStep().log(Status.INFO, " - Test Suite Completed");
        ExtentReportsNG.flushExtentReports(TestMembersFactory.getReport());
        ExtentManager.getInstance().removeExtentTestObject();
        TestMembersFactory.removeObjects();

    }

}
