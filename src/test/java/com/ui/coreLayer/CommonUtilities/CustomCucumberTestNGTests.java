package com.ui.coreLayer.CommonUtilities;

import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.coreLayer.FrameworkConfigs.ProjectConfigurations;
import com.ui.orchestrationLayer.Generics.ScenarioContext;
import com.ui.orchestrationLayer.Generics.TestParameters;
import com.ui.orchestrationLayer.enums.Browser;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Map;

public class CustomCucumberTestNGTests extends AbstractTestNGCucumberTests {

    private static final Logger logger = LoggerUtil.getLogger(CustomCucumberTestNGTests.class);
    protected static ThreadLocal<ITestContext> testListener = new ThreadLocal<ITestContext>();
    protected static WebDriver driver = null;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize WebDriver, set up reporting, etc.
        System.out.println("TESTNG- Before Suite");
        logger.info("TESTNG- Before Suite");
        setupBeforeSuite();
    }

    @AfterSuite
    public void afterSuite() {
        // Cleanup operations like closing WebDriver, saving reports, etc.
        System.out.println("TESTNG- After Suite");
        logger.info("TESTNG- After Suite");
        tearDownAfterSuite();
    }

    @BeforeTest
    public void beforeTest(ITestContext context) {
        // Initialize WebDriver, set up reporting, etc.
        System.out.println("TESTNG- Before Test: Initialize WebDriver");
        logger.info("\"TESTNG- Before Test: Initialize WebDriver");
        setupBeforeTestCase(context);
    }

    @AfterTest
    public void afterTest(ITestContext context) {
        // Initialize WebDriver, set up reporting, etc.
        System.out.println("TESTNG- After Test: Close the Browser");
        logger.info("\"TESTNG- After Test: Close the browser");
        setupAfterTestCase(context);
    }

    public static ITestContext getTestListener(){
        return CustomCucumberTestNGTests.testListener.get();
    }

    public static void setTestListener(ITestContext testListener){
        CustomCucumberTestNGTests.testListener.set(testListener);
    }


    public static void setupBeforeSuite(){
        System.out.println("Browser value is: "+getTestParameter("browser"));
        System.out.println("Environment value is: "+getTestParameter("env"));
        //System.out.println("execution mode value is: "+getTestParameter("executionmode"));

    }

    public static void tearDownAfterSuite(){

        if(DriverManager.getInstance().getDriver()!=null){
            DriverManager.getInstance().getDriver().quit();
        }
        logger.info("Tear down completed");

    }


    public static void setupBeforeTestCase(ITestContext context){
        System.out.println("TESTNG- Before Each Scenario/TestCase: "+ context.getName());
        logger.info("TESTNG- Before Each Scenario/TestCase: "+ context.getName());
        driver = chooseGridOrLocalDriver();
        driver.manage().window().maximize();

    }

    public static WebDriver chooseGridOrLocalDriver(){
        //WebDriver driver = null;
        String gridValue = getTestParameter("grid"); //ConfigurationReader.getProperty("grid");
        String gridUrl = getTestParameter("gridUrl"); //ConfigurationReader.getProperty("gridUrl");
        //Browser browser=TestParameters.getBrowser();
        Browser browser= Browser.valueOf(getTestParameter("browser"));

        if((gridValue.equalsIgnoreCase("true")) && (gridUrl!=null)){
            try {
                driver = DriverFactory.setRemoteDriver(browser, gridUrl);
            } catch (MalformedURLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            driver = DriverFactory.setDriver(browser);
        }
        DriverManager.getInstance().setDriver(driver);
        return DriverManager.getInstance().getDriver();

    }

    public static void setupAfterTestCase(ITestContext context){
        System.out.println("TESTNG- After Each Scenario/TestCase: "+ context.getName());
        logger.info("TESTNG- After Each Scenario/TestCase: "+ context.getName());
        if(DriverManager.getInstance().getDriver()!=null){
            DriverManager.getInstance().closeDriver();
        }
        TestParameters.getInstance().clearTestParameters();
        ScenarioContext.getInstance().clearScenarioContext();

    }

    private static String getTestParameter(String sParameter){

        try {
            if ((System.getProperty(sParameter) != null) && (!System.getProperty(sParameter).isEmpty())) {
                return System.getProperty(sParameter);
            }
            Map<String, String> caps = CustomCucumberTestNGTests.getTestListener().
                    getCurrentXmlTest().getLocalParameters();
            if (caps.containsKey(sParameter)) {
                return caps.get(sParameter);
            } else if (!(new ProjectConfigurations().getData(sParameter).isEmpty())) {
                return new ProjectConfigurations().getData(sParameter);
            } else {
                return "";
            }
        }
        catch (Exception e) {
                return "";
            }
        }


//    public static void setTestParameters(ITestResult result) {
//
//        Map<String, String> allParameters = result.getTestContext().getCurrentXmlTest().getAllParameters();
//        Browser browser = Browser.valueOf(allParameters.get("browser"));
//        TestParameters.setBrowser(browser);
//        //ExecutionMode executionMode = ExecutionMode.valueOf(allParameters.get("executionMode"));
//        //TestParameters.setExecutionMode(executionMode);
//    }


    }


