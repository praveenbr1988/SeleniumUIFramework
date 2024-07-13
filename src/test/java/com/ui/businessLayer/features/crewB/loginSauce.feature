Feature: Login functionality

  Scenario: Successful login
    Given I launch the "SauceDemo" application
    When the user enters valid credentials
    And clicks the login button
    Then the user should be redirected to the products page

  Scenario: Unsuccessful login with invalid credentials
    Given I launch the "SauceDemo" application
    When the user enters invalid credentials
    And clicks the login button
    Then an error message should be displayed