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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProductsPage extends BaseCucumberTest {

    private static final Logger logger = LoggerUtil.getLogger(ProductsPage.class);

    public ProductsPage(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
        super(driver,testStep,softAssert);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    WebElement inventoryItem;

    @FindBy(css = ".shopping_cart_badge")
    WebElement cartBadge;


    public void addItemToCart() {
        logger.info("Adding item to cart");
        inventoryItem.click();
    }

    public String getCartItemCount() {
        return cartBadge.getText();
    }

    public void verifyLandingPage(){
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        logger.info("User redirected to products page");
    }

}
