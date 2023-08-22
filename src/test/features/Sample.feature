
Feature: login account.
  @sample_tag_1
  Scenario: Login account success.
    Given I am on the login page
    When I login with username "admin" and password "admin"
    And I navigate to create product page
    Then I should see the welcome page

  @sample_tag_0
  Scenario Outline: Login account failed.
      Given I am on the login page
      When I login with username " <username>" and password "<password>"
      Then I should see the error message "<message>"

      Examples:
        | username | password | message |
        | admin    | admin    | Invalid username or password! |
        | admin    | admin    | Invalid username or password! |
        | admin    | admin    | Invalid username or password! |
        | admin    | admin    | Invalid username or password! |
        | admin    | admin    | Invalid username or password! |
