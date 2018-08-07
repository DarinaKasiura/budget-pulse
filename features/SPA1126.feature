Feature: Account functionality

  Background:
    Given I logged in to the application
    When I navigate to Manage my Accounts
@smoke
  Scenario: (Negative) Transfer Funds
    And I try to transfer funds to the same account
    Then I should see an error message
@smoke
  Scenario: Delete Account
    And I delete account
    Then I should not see it displayed