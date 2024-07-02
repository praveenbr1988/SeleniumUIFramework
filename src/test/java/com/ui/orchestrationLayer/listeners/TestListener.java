package com.ui.orchestrationLayer.listeners;

import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.ui.coreLayer.CommonUtilities.CustomCucumberTestNGTests.setTestListener;



public class TestListener implements ITestListener {

    protected static final Logger logger = LoggerUtil.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TESTNG- Before Each Scenario/TestCase: "+ result.getName());
        logger.info("TESTNG- Before Each Scenario/TestCase: "+ result.getName());
        //setTestParameters(result);

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("TestNG Annotation-Before TEst  Suite: " + context.getName());
        logger.info("TESTNG Annotation-Before Test Suite ");
        setTestListener(context);

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("TestNG Annotation-Test Suite finished: " + context.getName());
        logger.info("TESTNG Annotation-After Test Suite completed");
    }

}
