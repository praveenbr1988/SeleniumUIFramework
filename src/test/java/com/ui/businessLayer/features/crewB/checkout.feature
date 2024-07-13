Feature: Checkout process

  Scenario: Successful checkout
    Given the user is logged in
    And the user has items in the cart
    When the user proceeds to checkout
    And enters valid shipping information
    And completes the purchase
    Then the order confirmation should be displayed