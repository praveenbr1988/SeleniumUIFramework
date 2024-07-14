package com.ui.businessLayer.stepDefinitions.crewB;

import com.ui.businessLayer.businesscomponents.PageObjectManager;
import com.ui.businessLayer.pages.crewB.CartPage;
import com.ui.businessLayer.pages.crewB.CheckoutPage;
import com.ui.businessLayer.pages.crewB.LoginPage;
import com.ui.businessLayer.pages.crewB.ProductsPage;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CheckoutSteps {

    private static final Logger logger = LoggerUtil.getLogger(CartSteps.class);

    private LoginPage loginPage = PageObjectManager.getPageObject(LoginPage.class);
    private ProductsPage productsPage = PageObjectManager.getPageObject(ProductsPage.class);
    private CartPage cartPage = PageObjectManager.getPageObject(CartPage.class);
    private CheckoutPage checkoutPage = PageObjectManager.getPageObject(CheckoutPage.class);


    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        cartPage.clickCheckout();
        logger.info("Proceeded to checkout");
    }

    @When("enters valid shipping information")
    public void entersValidShippingInformation() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        logger.info("Entered valid shipping information");
    }

    @When("completes the purchase")
    public void completesThePurchase() {
        checkoutPage.clickFinish();
        logger.info("Completed the purchase");
    }

    @Then("the order confirmation should be displayed")
    public void theOrderConfirmationShouldBeDisplayed() {
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("Thank you for your order!"));
        logger.info("Order confirmation displayed");
    }


}
