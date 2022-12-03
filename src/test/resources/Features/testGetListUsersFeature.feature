Feature: Test get list users from reqres.in

  Scenario: Check that there are 12 users in list
    Given The uri to end point is set as 'api/users?page=2'
    And The get user list request is build
    Then User List contains 12 users