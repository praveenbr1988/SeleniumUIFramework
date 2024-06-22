package com.ui.orchestrationLayer.listeners;

import com.ui.coreLayer.CommonUtilities.ConfigurationReader;
import com.ui.coreLayer.CommonUtilities.DriverFactory;
import com.ui.coreLayer.CommonUtilities.DriverManager;
import com.ui.coreLayer.CommonUtilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;


public class TestListener implements ITestListener {

    protected static final Logger logger = LoggerUtil.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        logger.info("Test setup started");
        WebDriver driver = null;
        String gridValue = ConfigurationReader.getProperty("grid");
        String gridUrl = ConfigurationReader.getProperty("gridUrl");

        if((gridValue.equalsIgnoreCase("true")) && (gridUrl!=null)){
            try {
                driver = DriverFactory.setRemoteDriver("chrome", gridUrl);
            } catch (MalformedURLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            driver = DriverFactory.setDriver("chrome");
        }
        DriverManager.getInstance().setDriver(driver);
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
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite finished: " + context.getName());
        DriverManager.getInstance().getDriver().quit();
        logger.info("Tear down completed");
    }
}
