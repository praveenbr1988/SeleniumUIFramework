package com.ui.base;

import com.ui.pageObjects.HomePage;
import com.ui.utils.ConfigurationReader;
import com.ui.utils.DriverManager;
import com.ui.utils.LoggerUtil;
import com.ui.utils.YamlReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected static final Logger logger = LoggerUtil.getLogger(BaseTest.class);
    protected WebDriver driver;
    //String url = ConfigurationReader.getProperty("app.url");


    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Test setup started");
        String env = ConfigurationReader.getProperty("env");
        YamlReader.setEnvironment(env);
        ConfigurationReader.getEnvConfigValues();

        String url = ConfigurationReader.getProperty("app.url");
        System.out.println("Environment from config file: "+env);
        System.out.println(env+ " URL from YAML File: "+url);

        driver = DriverManager.getDriver(browser);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Tear down completed");
    }
}
