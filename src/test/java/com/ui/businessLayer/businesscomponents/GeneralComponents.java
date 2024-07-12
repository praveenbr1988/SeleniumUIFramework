package com.ui.businessLayer.businesscomponents;

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

    public void launchApplication(String application) throws Exception {
        //String env = ConfigurationReader.getProperty("env");
        String env = getTestParameter("env").toUpperCase();
        String url =TestEnvironmentConfigurations.getTestURL("APP-"+env);
        System.out.println("Environment from config file: "+env);
        System.out.println(env+ " URL from YAML File: "+url);

        if(url == null)
            System.out.println("Application URL is not defined");
        driver.get(url);
        System.out.println(" Application Launched : " +url);
    }


}
