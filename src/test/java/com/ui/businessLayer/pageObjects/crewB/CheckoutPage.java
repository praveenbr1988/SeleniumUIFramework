package com.ui.businessLayer.pageObjects.crewB;

import com.aventstack.extentreports.ExtentTest;
import com.ui.businessLayer.stepDefinitions.crewB.CartSteps;
import com.ui.coreLayer.FrameworkConfigs.BaseCucumberTest;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class CheckoutPage extends BaseCucumberTest {

    private static final Logger logger = LoggerUtil.getLogger(CheckoutPage.class);

    public CheckoutPage(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
        super(driver,testStep,softAssert);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement confirmationMessage;


    public void enterFirstName(String fName) {
        logger.info("Entering first name: " + fName);
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        logger.info("Entering last name: " + lName);
        lastName.sendKeys(lName);
    }

    public void enterPostalCode(String pCode) {
        logger.info("Entering postal code: " + pCode);
        postalCode.sendKeys(pCode);
    }

    public void clickContinue() {
        logger.info("Clicking continue button");
        continueButton.click();
    }

    public void clickFinish() {
        logger.info("Clicking finish button");
        finishButton.click();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

}
