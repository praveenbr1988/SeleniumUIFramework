package com.ui.pageObjects;

import com.ui.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;


public class HomePage {

    private static final Logger logger = LoggerUtil.getLogger(HomePage.class);

    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        logger.info("Entering search text: " + searchTerm);
        searchBox.sendKeys(searchTerm);
    }
}
