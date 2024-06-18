package com.ui.tests;

import com.ui.base.BaseTest;
import com.ui.pageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testGoogleSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchTerm("Selenium WebDriver");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }
}
