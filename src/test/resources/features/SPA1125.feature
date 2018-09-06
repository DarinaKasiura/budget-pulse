Feature: Add a note to a Recent Transaction
@tag
  Scenario: Add a note
    Given I logged in to the application
    When I navigate to Recent Transactions
    Then I should be able to add a note to a Transaction