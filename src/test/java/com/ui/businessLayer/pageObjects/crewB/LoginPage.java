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

public class LoginPage extends BaseCucumberTest {

    public LoginPage(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
        super(driver,testStep,softAssert);
        PageFactory.initElements(this.driver, this);
    }

    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = ".error-message-container")
    WebElement errorMessage;


    public void enterUsername(String user) {
        logger.info("Entering username: " + user);
        username.sendKeys(user);
    }

    public void enterPassword(String pass) {
        logger.info("Entering password: " + pass);
        password.sendKeys(pass);
    }

    public void clickLogin() {
        logger.info("Clicking login button");
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
