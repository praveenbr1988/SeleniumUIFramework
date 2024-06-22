package com.ui.businessLayer.pageObjects.crewA;

import com.ui.coreLayer.CommonUtilities.LoggerUtil;
import com.ui.orchestrationLayer.Generics.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class GooglePage {

    private static final Logger logger = LoggerUtil.getLogger(GooglePage.class);

    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        logger.info("Entering search text: " + searchTerm);
        searchBox.sendKeys(searchTerm);
        ScenarioContext.getInstance().setScenarioContext("searchTerm",searchTerm );

    }

    public void getBrowserTitle() throws InterruptedException {
        String title = driver.getTitle();
        Thread.sleep(5000);
        ScenarioContext.getInstance().setScenarioContext("Title",title );
        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }






}
