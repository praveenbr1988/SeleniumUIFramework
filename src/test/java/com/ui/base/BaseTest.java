package com.ui.base;

import com.ui.utils.ConfigurationReader;
import com.ui.utils.DriverManager;
import com.ui.utils.YamlReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    //String url = ConfigurationReader.getProperty("app.url");


    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
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
    }
}
