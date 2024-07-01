package com.ui.businessLayer.stepDefinitions.commonReusableSteps;

import io.cucumber.java.en.Given;

public class CommonSteps {

    @Given("The test data is loaded from {string}")
    public void the_test_data_is_loaded_from(String testDataPath) {
        System.out.println("the Test data path: "+testDataPath);
    }

}
