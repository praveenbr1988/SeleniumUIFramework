Feature: Login

  Background:
    Given The test data is loaded from "src/test/resources/testData/crewA/testdatasheet.xlsx"

  Scenario: Successful Login1
    Given The user is on the login page
    And The user enters valid credentials
    Then The user should be redirected to the dashboard

  Scenario: Successful Login2
    Given The user is on the login page
    And The user enters valid credentials
    Then The user should be redirected to the dashboard

  Scenario: Successful Login3
    Given The user is on the login page
    And The user enters valid credentials
    Then The user should be redirected to the dashboard

