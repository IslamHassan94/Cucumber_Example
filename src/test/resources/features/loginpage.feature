Feature: Login functionality for OpenCart E-commerce Website

  As a user of OpenCart website
  I want to be able to log in to my account
  So that I can process my account-related features and manage my orders

  Background:
    Given I am on the OpenCart login page

  Scenario: Successful login with valid credentials
    Given I have entered a valid username and password
    When  I click on the login button
    Then  I should be logged in successfully

  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username           | password         | error_message                                         |
      | invalid@email.com  | invalidPassword  | Warning: No match for E-Mail Address and/or Password. |
      | aabcccc            | validPassword    | Warning: No match for E-Mail Address and/or Password. |
      | valid@email.com    | abcccc           | Warning: No match for E-Mail Address and/or Password. |

    Scenario: Navigating to the forgotten password page
      When I click on the "Forgotten Password" link
      Then I should be redirected to the password reset page