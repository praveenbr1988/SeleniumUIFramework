package com.ui.tests;

import com.ui.base.BaseTest;
import com.ui.pageObjects.HomePage;
import com.ui.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(HomePageTest.class);
    protected WebDriver driver;

    @Test
    public void testGoogleSearch() throws InterruptedException {
        logger.info("Google Search test started");
        driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchTerm("Selenium WebDriver");
        String title = driver.getTitle();
        Thread.sleep(10000);
        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }
}
