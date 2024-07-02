package com.ui.businessLayer.businesscomponents;

import com.ui.coreLayer.CommonUtilities.ConfigurationReader;
import com.ui.coreLayer.CommonUtilities.DriverManager;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.coreLayer.CommonUtilities.YamlReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GeneralComponents {

    protected static final Logger logger = LoggerUtil.getLogger(GeneralComponents.class);
    protected WebDriver driver = DriverManager.getInstance().getDriver();

    public void launchApplication(String application){
        String env = ConfigurationReader.getProperty("env");
        YamlReader.setEnvironment(env);
        ConfigurationReader.getEnvConfigValues();

        String url = ConfigurationReader.getProperty("app.url");
        System.out.println("Environment from config file: "+env);
        System.out.println(env+ " URL from YAML File: "+url);

        if(url == null)
            System.out.println("Application URL is not defined");
        driver.get(url);
        System.out.println(" Application Launched : " +url);
    }


}
