package com.ui.businessLayer.businesscomponents;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.coreLayer.FrameworkConfigs.TestEnvironmentConfigurations;
import com.ui.orchestrationLayer.Generics.TestMembersFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests.getTestParameter;

public class GeneralComponents {

    protected static final Logger logger = LoggerUtil.getLogger(GeneralComponents.class);
    //protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected WebDriver driver = TestMembersFactory.getDriver();
    protected ExtentTest testStep = TestMembersFactory.getTestStep();

    public void launchApplication(String application) throws Exception {
        //String env = ConfigurationReader.getProperty("env");
        String env = getTestParameter("env").toUpperCase();
        String url =TestEnvironmentConfigurations.getTestURL("APP-"+env);
        System.out.println("Environment from config file: "+env);
        System.out.println(env+ " URL from YAML File: "+url);

        if(url == null){
            testStep.log(Status.FAIL, "Application URL is not defined and Not able to launch Application");
            System.out.println("Application URL is not defined");
        }
        try{
            driver.get(url);
            System.out.println(" Application Launched : " +url);
            testStep.log(Status.PASS, " Application Launched : " +url);
        }catch(Exception e){
            testStep.log(Status.FAIL, " Issue with the driver Setup. Following Exception thrown:  " +e);
        }

    }


}
