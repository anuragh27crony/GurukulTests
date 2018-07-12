Feature: Branches Scenario

  Scenario: Successfully Create a New Branch
    Given User Login successfully
    And User navigates to Branch Page
    When User creates a New Branch
    Then New branch should be created successfully


  Scenario: Create a New Branch with branch name exceeding char limit
    Given User Login successfully
    And User navigates to Branch Page
    When User creates a New Branch with branch name exceeding char limit
    Then Error message for exceeding charlimit should be displayed
    And New branch should not be created


  Scenario: Create a New Branch with branch name less than min char limit
    Given User Login successfully
    And User navigates to Branch Page
    When User creates a New Branch with branch name less than min char limit
    Then Error message for minimum char limit should be displayed
    And New branch should not be created

  Scenario: Create a New Branch with invalid branch Name
    Given User Login successfully
    And User navigates to Branch Page
    When User creates a New Branch with invalid branch Name
    Then Error message for invalid branch code should be displayed
    And New branch should not be created

  Scenario: Create a New Branch with invalid branch code
    Given User Login successfully
    And User navigates to Branch Page
    When User creates a New Branch with invalid branch code
    Then Error message for invalid branch code should be displayed
    And New branch should not be created


  Scenario: View Branch details
    Given User Login successfully
    And User navigates to Branch Page
    When User views a Branch details
    Then branch details should be displayed correctly


  Scenario: Edit an Existing Branch with valid details
    Given User Login successfully
    And User navigates to Branch Page
    When User edits an existing Branch with valid details
    Then branch details should be updated successfully


  Scenario: Edit an Existing Branch with invalid details
    Given User Login successfully
    And User navigates to Branch Page
    When User edits an existing Branch with invalid details
    Then branch details should not be updated successfully


  Scenario: Delete an Existing Branch with No Staff Assigned
    Given User Login successfully
    And User navigates to Branch Page
    When User deletes an existing Branch with no staff assigned
    Then branch details should be deleted successfully


  Scenario: Delete an Existing Branch with Staff Assigned
    Given User Login successfully
    And User navigates to Branch Page
    When User deletes an existing Branch with staff assigned
    Then branch details should not be deleted


  Scenario: Search for Existing Branch
    Given User Login successfully
    And User navigates to Branch Page
    When User searches for existing Branch
    Then Matching Search results should be displayed correctly


  Scenario: Search for Branch with wild card *
    Given User Login successfully
    And User navigates to Branch Page
    When User searches for Branch using wildcard
    Then All branches should be displayed