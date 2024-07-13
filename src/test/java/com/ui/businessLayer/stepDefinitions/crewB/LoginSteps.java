package com.ui.businessLayer.stepDefinitions.crewB;

import com.ui.businessLayer.businesscomponents.GeneralComponents;
import com.ui.businessLayer.businesscomponents.PageObjectManager;
import com.ui.businessLayer.pageObjects.crewB.LoginPage;
import com.ui.businessLayer.pageObjects.crewB.ProductsPage;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    private static final Logger logger = LoggerUtil.getLogger(LoginSteps.class);
    private LoginPage loginPage = PageObjectManager.getPageObject(LoginPage.class);
    private ProductsPage productsPage = PageObjectManager.getPageObject(ProductsPage.class);
    private GeneralComponents general = new GeneralComponents();


    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        logger.info("Entered valid credentials");
    }

    @When("the user enters invalid credentials")
    public void theUserEntersInvalidCredentials() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_user");
        logger.info("Entered invalid credentials");
    }

    @When("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickLogin();
        //loginPage.clickLogin();
        logger.info("Clicked login button");
    }

    @Then("the user should be redirected to the products page")
    public void theUserShouldBeRedirectedToTheProductsPage() {
        productsPage.verifyLandingPage();

    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
        logger.info("Error message displayed");
    }

}
