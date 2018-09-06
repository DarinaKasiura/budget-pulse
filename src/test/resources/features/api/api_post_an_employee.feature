Feature: Employee Rest Api requests
@ApiPost
  Scenario: Post an employee then generate a response
    Given Content type and Accept type is Json
    When I post a new Employee with "random" id
    Then Status code is 201
    And Response Json should contain Emloyee info
    When I send a GET request with "random" id
    Then Status code is 200
    And employee Json Response Data should match the posted Json data
