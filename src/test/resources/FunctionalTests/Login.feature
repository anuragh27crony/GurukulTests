Feature: Login Scenarios

  Scenario: Successfully login with Valid Credentials
    Given User navigates to Gurukul Homepage
    And User selects Login
    When User fills valid username and password
    Then Login should be successful
    And User should see UserNavigationBar

  Scenario: Unsuccessful login with Invalid Credentials
    Given User navigates to Gurukul Homepage
    And User selects Login
    When User fills invalid username and password
    Then Login should not be successful
    And Error message for Invalid Credentials should be displayed


  Scenario: Unsuccessful login with Empty Username
    Given User navigates to Gurukul Homepage
    And User selects Login
    When User fills password with empty Username
    Then Login should not be successful
    And Error message for Invalid Credentials should be displayed


  Scenario: Unsuccessful login with Empty password for Valid Username
    Given User navigates to Gurukul Homepage
    And User selects Login
    When User fills Valid Username with empty password
    Then Login should not be successful
    And Error message for Invalid Credentials should be displayed