Feature: Add Method
@add
  Scenario Outline: Add Method Verification
    When I "<first>" and "<second>"
    Then I verify the "<result>" in step

    Examples: Positive Scenario
      | first | second | result |
      |     1 |      2 |      3 |
    Examples: Negative Scenario
      | first | second | result                  |
      | 'a'   | 'b'    | 'NumberFormatException' |
