Feature: Login Page Feature

  @smoke
  Scenario: Verify Login page title
    Given user is on login page
    When  user gets login page title
    Then Page title should be "Account Login"

  @smoke @regression
  Scenario Outline: Verify login to application with proper credentials
    Given user is on login page
    When user enter <username> and <password> and click on login button
    Then user should login to application
    Examples:
      | username                  | password   |
      | ritishkommana24@gmail.com | Ritish@424 |

  @regression
  Scenario: Verify new user registration error message
    Given user is on login page
    When user enter below details in register page
      | firstName | lastName | email | phone | password | confirmPassword|
      | Mira | Y | ymira@gmail.com | 9900990099 | ymira123 | ymira123 |
      | Ryan | I | iryan@gmail.com | 9988998899 | iryan123 | iryan123 |
    Then user should get registration error

  @regression
  Scenario: Verify login without examples keyword
    Given user is on login page
    When user enter "ritishkommana24@gmail.com" and "Ritish@424" and click on login
    Then user should login to application