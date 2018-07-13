Feature: Staff Scenario

  Scenario: Successfully Create a New Staff
    Given User Logins successfully
    And User navigates to Staff Page
    When User creates a New Staff
    Then New staff should be created successfully


  Scenario: Create a New Staff with Staff name exceeding char limit
    Given User Logins successfully
    And User navigates to Staff Page
    When User creates a New Staff with Staff name exceeding char limit
    Then Error message for exceeding charlimit should be displayed
    And New Staff should not be created


  Scenario: Create a New Staff with Staff name less than min char limit
    Given User Logins successfully
    And User navigates to Staff Page
    When User creates a New Staff with Staff name less than min char limit
    Then Error message for minimum char limit should be displayed
    And New Staff should not be created

  Scenario: Successfully Create a New Staff with Empty Branch
    Given User Logins successfully
    And User navigates to Staff Page
    When User creates a New Staff with Empty Branch
    Then New staff should be created successfully

  Scenario: View Staff details
    Given User Logins successfully
    And User navigates to Staff Page
    When User views a Staff details
    Then staff details should be displayed correctly

  Scenario: Edit an Existing Staff with valid details
    Given User Logins successfully
    And User navigates to Staff Page
    When User edits an existing Staff with valid details
    Then staff details should be updated successfully


  Scenario: Edit an Existing Staff with invalid details
    Given User Logins successfully
    And User navigates to Staff Page
    When User edits an existing Staff with invalid details
    Then staff details should not be updated successfully


  Scenario: Delete an Existing Staff
    Given User Logins successfully
    And User navigates to Staff Page
    When User deletes an existing Staff
    Then staff details should be deleted successfully


  Scenario: Search for Existing Staff
    Given User Logins successfully
    And User navigates to Staff Page
    When User searches for existing Staff
    Then Matching Search results should be displayed correctly


  Scenario: Search for Staff with wild card *
    Given User Logins successfully
    And User navigates to Staff Page
    When User searches for Staff using wildcard
    Then All staff data should be displayed
