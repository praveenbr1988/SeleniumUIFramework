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

public class CartPage extends BaseCucumberTest {

    private static final Logger logger = LoggerUtil.getLogger(CartPage.class);

    public CartPage(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
        super(driver,testStep,softAssert);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(className = "cart_item")
    WebElement cartItem;

    @FindBy(css = ".shopping_cart_badge")
    WebElement cartBadge;


    public void clickCheckout() {
        logger.info("Clicking checkout button");
        checkoutButton.click();
    }

    public boolean isCartItemPresent() {
        return cartItem.isDisplayed();
    }

    public void removeCartItem() {
        logger.info("Removing item from cart");
        cartItem.click();
    }

    public String getCartItemCount() {
        return cartBadge.getText();
    }

}
