package com.ui.businessLayer.stepDefinitions.crewA;

import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.orchestrationLayer.Generics.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class LoginSteps {

    private static final Logger logger = LoggerUtil.getLogger(LoginSteps.class);
    protected WebDriver driver;

    @Given("The user is on the login page")
    public void user_is_on_login_page() {
        // Implement code to navigate to the login page
        System.out.println("Hi");
    }

    @When("The user enters valid credentials")
    public void user_enters_valid_credentials() {
        // Implement code to enter valid credentials
        System.out.println("Hello");
    }

    @Then("The user should be redirected to the dashboard")
    public void user_redirected_to_dashboard() {
        // Implement code to verify the user is redirected to the dashboard
        System.out.println("How r u");
    }


    @Then("Print the Search context values")
    public void Print_the_Searchcontext_values() {
        // Implement code to verify the user is redirected to the dashboard
        System.out.println("Search Term: "+ ScenarioContext.getInstance().getScenarioContext("searchTerm"));
        System.out.println("Title: "+ ScenarioContext.getInstance().getScenarioContext("Title"));

    }

}
