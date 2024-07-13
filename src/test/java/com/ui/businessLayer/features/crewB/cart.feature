@cart
Feature: Cart functionality

  Scenario: Add items to the cart
    Given the user is logged in
    When the user adds an item to the cart
    Then the cart item count should be updated
    Then the user removes an item from the cart
