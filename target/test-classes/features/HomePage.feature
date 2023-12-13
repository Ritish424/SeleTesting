Feature: Home Page Feature

  @smoke @regression
  Scenario: Verify Home page title
    Given user is on home page
    When  user gets home page title
    Then Page title should be "Your Store"

  @smoke @regression
  Scenario: Verify search for iphone
    Given user is on home page
    When user search for iphone
    Then iphone results should display