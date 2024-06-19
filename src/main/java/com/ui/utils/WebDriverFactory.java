package com.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
               // WebDriverManager.chromedriver().setup();
                webDriver.set(new ChromeDriver());
                break;
            case "firefox":
               // WebDriverManager.firefoxdriver().setup();
                webDriver.set(new FirefoxDriver());
                break;
            case "edge":
                //WebDriverManager.edgedriver().setup();
                webDriver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
