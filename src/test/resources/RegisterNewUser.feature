Feature: Register new User Scenarios

  @bug
  Scenario: Successfully Register new User with Valid details
    Given User navigates to Gurukul Homepage
    And User selects Register New User
    When User registers with valid user details and password
    Then User Registration should be successful
    And Login for New User should be successful


  Scenario: Successfully Register new User with InValid login
    Given User navigates to Gurukul Homepage
    And User selects Register New User
    When User registers with invalid login name
    Then Error message for Invalid login name should be displayed
    And User Registration should not be successful


  Scenario: Successfully Register new User with Invalid email
    Given User navigates to Gurukul Homepage
    And User selects Register New User
    When User registers with invalid email
    Then Error message for Invalid email should be displayed
    And User Registration should not be successful


  Scenario: Successfully Register new User with Invalid password
    Given User navigates to Gurukul Homepage
    And User selects Register New User
    When User registers with invalid password
    Then Error message for invalid password should be displayed
    And User Registration should not be successful


  Scenario: Successfully Register new User with non matching confirmation password
    Given User navigates to Gurukul Homepage
    And User selects Register New User
    When User registers with non matching confirmation password
    Then Error message for invalid password should be displayed
    And User Registration should not be successful