package com.ui.businessLayer.businesscomponents;

import com.aventstack.extentreports.ExtentTest;
import com.ui.orchestrationLayer.Generics.TestMembersFactory;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class PageObjectManager {

    public static <PageObject> PageObject getPageObject(Class<PageObject> pageObject) {

        try {
            return pageObject.getConstructor(WebDriver.class, ExtentTest.class, SoftAssert.class).newInstance(
                    TestMembersFactory.getDriver(), TestMembersFactory.getTestStep(), TestMembersFactory.getSoftAssert());
        } catch (Exception e) {
            return null;
        }
    }

}
