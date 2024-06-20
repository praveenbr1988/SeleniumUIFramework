package com.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver.set(new ChromeDriver());
                break;
            case "firefox":
                webDriver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                webDriver.set(new EdgeDriver(options));
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

//Using Remote webdriver for Selenium grid execution
    //To watch Selenium grid console- use this IP- http://localhost:4444/grid/console
    public static void setRemoteDriver(String browser,String gridUrl) throws MalformedURLException, FileNotFoundException {
        URL hubUrl = new URL(gridUrl); // Replace {hub-ip} with your Hub's IP address or hostname in the config properties

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = loadChromeOptionsFromYaml();
                DesiredCapabilities dc = new DesiredCapabilities(chromeOptions);
                dc.setBrowserName("chrome");
                dc.setPlatform(Platform.LINUX);
                webDriver.set(new RemoteWebDriver(hubUrl, dc));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                webDriver.set(new RemoteWebDriver(hubUrl, firefoxOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                webDriver.set(new RemoteWebDriver(hubUrl, edgeOptions));
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


    private static ChromeOptions loadChromeOptionsFromYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream inputStream = new FileInputStream("src/main/resources/common/chromeconfigurations.yaml");
        Map<String, Object> chromeConfig = yaml.load(inputStream);

        ChromeOptions options = new ChromeOptions();
        if (chromeConfig.containsKey("chromeOptions")) {
            Map<String, Object> chromeOptions = (Map<String, Object>) chromeConfig.get("chromeOptions");
            for (Map.Entry<String, Object> entry : chromeOptions.entrySet()) {
                switch (entry.getKey()) {
                    case "disableGpu":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--disable-gpu");
                        }
                        break;
                    case "disableExtensions":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--disable-extensions");
                        }
                        break;
                    case "incognito":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--incognito");
                        }
                        break;
                    case "disableInfobars":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--disable-infobars");
                        }
                        break;
                    case "startMaximized":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--start-maximized");
                        }
                        break;
                    case "ignoreCertificateErrors":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--ignore-certificate-errors");
                        }
                        break;
                    case "windowSize":
                        options.addArguments("--window-size=" + entry.getValue());
                        break;
                    case "remoteDebuggingPort":
                        options.addArguments("--remote-debugging-port=" + entry.getValue());
                        break;
                    case "disableDevShmUsage":
                        if ((Boolean) entry.getValue()) {
                            options.addArguments("--disable-dev-shm-usage");
                        }
                        break;
                    case "headless":
                        if ((Boolean) entry.getValue()) {
                            //options.setHeadless(true);
                            options.addArguments("--headless");

                        }
                        break;
                    default:
                        // Handle other options if needed
                        break;
                }
            }
        }

        return options;
    }
}
