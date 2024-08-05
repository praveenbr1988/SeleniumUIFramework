Feature: Login

  Scenario: Successful Login
    Given I launch the Google application
    And Search text in Google
    Then Print the Search context values

@Login
  Scenario: Successful Login
    Given I launch the FB application
#    And Search text in Google
#    Then Print the Search context values
