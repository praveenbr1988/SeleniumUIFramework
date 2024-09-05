package com.ui.coreLayer.FrameworkConfigs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ui.coreLayer.UIActionUtils.ElementReadyStatus;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;


import java.text.SimpleDateFormat;
import java.util.Date;



public class ExtentReportsNG {

    static ExtentReports extent;
    private static final Logger logger = LoggerUtil.getLogger(ExtentReportsNG.class);
    private static String errorMessageTable = "<TABLE style='border=2px solid black; table-layout: fixed'>";

    public static ExtentReports setupExtentReport(){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        Date date =new Date();
        String actualDate = format.format(date);
        String reportPath = System.getProperty("user.dir") + "/DetailedReports/ExecutionReport_" + actualDate + ".html";
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReport);

        sparkReport.config().setDocumentTitle("DocumentTitle");
        sparkReport.config().setTheme(Theme.DARK);
        sparkReport.config().setReportName("ReportName");

        extent.setSystemInfo("Executed on Browser: ", CustomCucumberTestNGTests.getTestParameter("browser"));
        extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User: ",System.getProperty("user.name"));

        return extent;

    }

    public static void flushExtentReports(ExtentReports extentObj){
        extentObj.flush();
    }

    public static void formatCucumberStepResults(String message) {
        logger.info(message);
    }

    public static String getFileNameDateTimeStamp() {
        try {
            return (new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss")).format(new Date());
        } catch (Exception var1) {
            logger.error("Get Date Error - ", var1);
            return null;
        }
    }

    public static void logTestNGInfoTestStep(ExtentTest testStep, boolean cucumberTest, String message) {
        String ignoreReportingValue = (new ProjectConfigurations()).getData("ignoreInformationalReporting") == null ? "false" : (new ProjectConfigurations()).getData("ignoreInformationalReporting");
        if (ignoreReportingValue.equalsIgnoreCase("true") && !cucumberTest) {
            testStep.log(Status.INFO, message);
        }
    }

    public static void logTestStepInformation(ExtentTest testStep, boolean cucumberTest, String message) {
        if (cucumberTest) {
            formatCucumberStepResults(message);
        } else {
            testStep.log(Status.INFO, message);
        }
    }


    public static void logPassTestStep(ExtentTest testStep, boolean cucumberTest, String message) {
        if (!cucumberTest) {
            testStep.log(Status.PASS, message);
        } else {
            formatCucumberStepResults(message);
        }
    }

    public static void logFailTestStep(ExtentTest testStep, boolean cucumberTest, String message) {
        if (!cucumberTest) {
            testStep.log(Status.FAIL, message);
        } else {
            formatCucumberStepResults(message);
        }
    }

    public static String formatTestSkippedMessage(String skipTitle, String skipMessage) {
        String errorMessage = errorMessageTable + "<TR style='border:2px solid black; width: 200px'><B>Title</B></TD><TD><SPAN style='font-weight:bold'>" + skipTitle + "</SPAN></TD></TR>";
        errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black'><B>Skip Message</B></TD><TD><SPAN style='font-weight:bold'>" + skipMessage + "</SPAN></TD></TR>";
        errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black'><B>Test Status</B></TD><TD style='background-color:#fff733'><SPAN style='font-weight:bold'>SKIPPED</SPAN></TD></TR></TABLE>";
        return errorMessage;
    }

    public static void logFailedExceptionMessage(ITestResult testResult, ExtentTest testStep) {
        if (testResult.getThrowable() != null) {
            StackTraceElement[] stackTraceElements = testResult.getThrowable().getStackTrace();
            if (!stackTraceElements[0].getClassName().contains("org.testng.asserts")) {
                String var10000 = testResult.getThrowable().toString();
                String stackTraceString = var10000 + " on line " + stackTraceElements[0].getLineNumber() + " in " + stackTraceElements[0].getClassName() + " of test " + stackTraceElements[0].getMethodName();
                logFailTestStep(testStep, false, formatTestFailedMessage("Test Exception Thrown",stackTraceString));
            }

        }
    }

    public static String formatTestFailedMessage(String failTitle, String failMessage) {
        String errorMessage = errorMessageTable + "<TR style='border:2px solid black; width: 50px'><B>Title</B></TD><TD><SPAN style='font-weight:bold'>" + failTitle + "</SPAN></TD></TR>";
        errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black'><B>Failure Message</B></TD><TD><SPAN style='font-weight:bold'>" + failMessage + "</SPAN></TD></TR>";
        errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black'><B>Test Status</B></TD><TD style='background-color:#ff0000'><SPAN style='font-weight:bold'>FAILED</SPAN></TD></TR></TABLE>";
        return errorMessage;
    }

    public static String formatPassResults(String expectedResult) {
        String errorMessage = errorMessageTable + "<TR style='border:2px solid black; width: 200px'><B>Expected Result</B></TD><TD><SPAN style='font-weight:bold'>" + expectedResult + "</SPAN></TD></TR>";
        errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black'><B>Test Status</B></TD><TD style='background-color:green'><SPAN style='font-weight:bold'>PASSED</SPAN></TD></TR></TABLE>";
        return errorMessage;
    }

}