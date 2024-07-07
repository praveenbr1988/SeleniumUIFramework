package com.ui.coreLayer.FrameworkConfigs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ui.coreLayer.CommonUtilities.CustomCucumberTestNGTests;

import java.text.SimpleDateFormat;
import java.util.Date;



public class ExtentReportsNG {

    static ExtentReports extent;

    public static ExtentReports setupExtentReport(){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        Date date =new Date();
        String actualDate = format.format(date);
        String reportPath = System.getProperty("user.dir") + "/Reports/ExecutionReport_" + actualDate + ".html";
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



}
