package com.ui.businessLayer.stepDefinitions.crewB;

import com.ui.businessLayer.businesscomponents.GeneralComponents;
import com.ui.businessLayer.businesscomponents.PageObjectManager;
import com.ui.businessLayer.pageObjects.crewB.CartPage;
import com.ui.businessLayer.pageObjects.crewB.LoginPage;
import com.ui.businessLayer.pageObjects.crewB.ProductsPage;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartSteps {

    private static final Logger logger = LoggerUtil.getLogger(CartSteps.class);
    private LoginPage loginPage = PageObjectManager.getPageObject(LoginPage.class);
    private ProductsPage productsPage = PageObjectManager.getPageObject(ProductsPage.class);
    private CartPage cartPage = PageObjectManager.getPageObject(CartPage.class);
    GeneralComponents generalComponents = new GeneralComponents();

    @Given("the user is logged in")
    public void theUserIsLoggedIn() throws Exception {
        generalComponents.launchApplication("SauceDemo");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        logger.info("User logged in");
    }

    @When("the user adds an item to the cart")
    public void theUserAddsAnItemToTheCart() {
        productsPage.addItemToCart();
        logger.info("Item added to cart");
    }

    @Then("the cart item count should be updated")
    public void theCartItemCountShouldBeUpdated() {
        Assert.assertEquals(productsPage.getCartItemCount(), "1");
        logger.info("Cart item count updated");
    }

    @Given("the user has items in the cart")
    public void theUserHasItemsInTheCart() {
        productsPage.addItemToCart();
        logger.info("User has items in the cart");
    }

    @When("the user removes an item from the cart")
    public void theUserRemovesAnItemFromTheCart() {
        cartPage.removeCartItem();
        logger.info("Item removed from cart");
    }


}
