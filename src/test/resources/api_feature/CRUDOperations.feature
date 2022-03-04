@api
Feature:

  @api
  Scenario Outline:
    Given Create user with "<name>" and "<job>"
    And Validate that status code is 201
    And Make GET call to get user with "<URL>"
    And Validate that status code is 200
    And Updating the user with the following data
      | name   | Batch 3 |
      | status | active  |
    When I delete user
    Then Validate that status code is 204

    Examples:
      | name     | job    | URL                         |
      | morpheus | leader | https://reqres.in/api/users |
