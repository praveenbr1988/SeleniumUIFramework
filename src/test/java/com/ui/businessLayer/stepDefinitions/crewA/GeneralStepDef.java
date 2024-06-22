package com.ui.businessLayer.stepDefinitions.crewA;

import com.ui.businessLayer.businesscomponents.GeneralComponents;
import com.ui.businessLayer.pageObjects.crewA.GooglePage;
import com.ui.coreLayer.CommonUtilities.DriverManager;
import com.ui.coreLayer.CommonUtilities.LoggerUtil;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class GeneralStepDef {

    private static final Logger logger = LoggerUtil.getLogger(GeneralStepDef.class);

    protected WebDriver driver = DriverManager.getInstance().getDriver();

    GeneralComponents general = new GeneralComponents();
    GooglePage gPage = new GooglePage(driver);

    @Given ("^I launch the (.+)$")
    public void i_launch_the_application(String application) throws InterruptedException {
        general.launchApplication(application);
        logger.info("Successfully launched");
    }

    @Given ("^Search text in Google$")
    public void search_text_google() throws InterruptedException {
        gPage.enterSearchTerm("Hurray Cucumber Feature executed");
        gPage.getBrowserTitle();
        logger.info("serached successfully");

    }


}
