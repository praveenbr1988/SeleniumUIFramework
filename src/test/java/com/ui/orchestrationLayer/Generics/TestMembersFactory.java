package com.ui.orchestrationLayer.Generics;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class TestMembersFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();
    private static ThreadLocal<ExtentTest> tltestStep = new ThreadLocal();
    private static ThreadLocal<ExtentReports> tlReport = new ThreadLocal();
    private static ThreadLocal<SoftAssert> tlSoftAssert = new ThreadLocal();

    private TestMembersFactory() {
    }


    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    public static ExtentTest getTestStep() {
        return tltestStep.get();
    }

    public static void setTestStep(ExtentTest testStep) {
        tltestStep.set(testStep);
    }

    public static ExtentReports getReport() {
        return tlReport.get();
    }

    public static void setReport(ExtentReports report) {
        tlReport.set(report);
    }

    public static SoftAssert getSoftAssert() {
        return tlSoftAssert.get();
    }

    public static void setSoftAssert() {
        tlSoftAssert.set(new SoftAssert());
    }

    public static void removeObjects() {
        tlDriver.remove();
        tlReport.remove();
        tltestStep.remove();
        tlSoftAssert.remove();
    }

    public static void assertAll() {
        if (tlSoftAssert.get() != null)
            tlSoftAssert.get().assertAll();

    }

}
