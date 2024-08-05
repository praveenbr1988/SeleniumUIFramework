Feature: Login

  Background:
    Given The test data is loaded from "src/test/resources/testData/crewA/testdatasheet.xlsx"

  Scenario: Successful Login1
    Given The user is on the login page
    And The user enters valid credentials
    Then The user should be redirected to the dashboard

    @Sample
  Scenario: Verify Reading Environments yaml file
    Given Read Environments yaml file

      @Sample
  Scenario: Successful Login3
    Given The user is on the login page
    And The user enters valid credentials
    Then The user should be redirected to the dashboard

