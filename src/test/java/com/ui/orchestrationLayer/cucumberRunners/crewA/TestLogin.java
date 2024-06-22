package com.ui.orchestrationLayer.cucumberRunners.crewA;

import com.ui.coreLayer.CommonUtilities.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLogin {

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        // Implement your test logic here
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return ExcelUtils.getTestData("LoginData");
    }
}
