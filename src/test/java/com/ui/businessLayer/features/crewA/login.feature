Feature: Login

  Scenario: Successful Login
    Given I launch the "Google"
    And Search text in Google
    Then Print the Search context values

@Login
  Scenario: Successful Login
    Given I launch the "FB"
    And Search text in Google
    Then Print the Search context values
