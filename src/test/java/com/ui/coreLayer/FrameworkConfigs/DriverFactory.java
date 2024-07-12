package com.ui.coreLayer.FrameworkConfigs;

import com.ui.orchestrationLayer.enums.Browser;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverFactory {


    public static WebDriver setDriver(Browser browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments("--silent");
                driver = new ChromeDriver(cOptions);
                driver.manage().window().maximize();
                break;
            case CHROME_HEADLESS:
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless");
                chOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chOptions);
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                //System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
                EdgeOptions Edoptions = new EdgeOptions();
                Edoptions.addArguments("--disable-gpu");
                Edoptions.addArguments("--no-sandbox");
                Edoptions.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(Edoptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }

    //Using Remote webdriver for Selenium grid execution
    //To watch Selenium grid console- use this IP- http://localhost:4444/grid/console
    public static RemoteWebDriver setRemoteDriver(Browser browser, String gridUrl) throws MalformedURLException, FileNotFoundException {
        URL hubUrl = new URL(gridUrl); // Replace {hub-ip} with your Hub's IP address or hostname in the config properties
        RemoteWebDriver driver = null;

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = loadChromeOptionsFromYaml();
                DesiredCapabilities dc = new DesiredCapabilities(chromeOptions);
                dc.setBrowserName("chrome");
                dc.setPlatform(Platform.LINUX);
                driver = new RemoteWebDriver(hubUrl, dc);
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(hubUrl, firefoxOptions);
                driver.manage().window().maximize();
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new RemoteWebDriver(hubUrl, edgeOptions);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        return driver;
    }


//    public static void quitDriver() {
//        if (webDriver.get() != null) {
//            webDriver.get().quit();
//            webDriver.remove();
//        }
//    }


    private static ChromeOptions loadChromeOptionsFromYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream inputStream = new FileInputStream("src/test/resources/yamlFiles/chromeconfigurations.yaml");
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

    public static void captureLogs(WebDriver driver) {
        if (driver != null) {
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logEntries) {
                System.out.println(entry.getLevel() + " " + entry.getMessage());
            }
        }
    }


}
