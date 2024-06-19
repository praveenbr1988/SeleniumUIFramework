package com.ui.base;

import com.ui.pageObjects.HomePage;
import com.ui.utils.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class BaseTest {

    protected static final Logger logger = LoggerUtil.getLogger(BaseTest.class);
    protected WebDriver driver;
    //String url = ConfigurationReader.getProperty("app.url");



    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException, FileNotFoundException {
        logger.info("Test setup started");
        String env = ConfigurationReader.getProperty("env");
        YamlReader.setEnvironment(env);
        ConfigurationReader.getEnvConfigValues();

        String url = ConfigurationReader.getProperty("app.url");
        System.out.println("Environment from config file: "+env);
        System.out.println(env+ " URL from YAML File: "+url);

        String gridValue = ConfigurationReader.getProperty("grid");
        String gridUrl = ConfigurationReader.getProperty("gridUrl");

        if((gridValue.equalsIgnoreCase("true")) && (gridUrl!=null)){
            WebDriverFactory.setRemoteDriver(browser,gridUrl);
            getDriver().get(url);
        }
        else{
            WebDriverFactory.setDriver(browser);
            getDriver().get(url);
        }

    }

    public WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        //DriverManager.quitDriver();
        WebDriverFactory.quitDriver();
        logger.info("Tear down completed");
    }
}
