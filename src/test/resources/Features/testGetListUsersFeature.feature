Feature: Test get list users from reqres.in

  Scenario: Check that there are 12 users in list
    Given The uri to end point is set as 'api/users?page=2'
    And The get user list request is build
    Then User List contains 6 users

  Scenario: Check that first user's name is Michael
    Given The uri to end point is set as 'api/users?page=2'
    And The get user list request is build
    And First user name is 'Michael'

  Scenario Outline: User loggs in successfuly
    Given The uri to end point is set as 'api/login'
    And The user '<login>' and '<password>' are set
    And Post request is sent
    Then User token is received
    Examples:
      | login              | password   |
      | eve.holt@reqres.in | cityslicka |
      | eve.holt@reqres.in | cityslicka |
      | eve.holt@reqres.in | cityslicka |
      | eve.holt@reqres.in | cityslicka |
      | eve.holt@reqres.in | cityslicka |
      | eve.holt@reqres.in | cityslicka |