Feature: Navigation

  Background: Logged in as a Manager
    Given The manager is logged in as a manager
    Given The manager is on the home page

  Scenario: Home Page Links Visible
    Then The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview

  Scenario Outline: All Links Viable and Back Navigation
    Then The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview
    When The manager clicks on <link>
    Then The title of page should be <title>
    When The manager clicks the browser back button
    Then The manager should be on the home page and the title of page is Home


    Examples:
      | link           | title               |
      | "Matrices"       | "Matrices"     |
      | "Matrices"       | "Matrix Overivew"     |
      | "Test Cases"     | "Test Case Dashboard" |
      | "Test Cases"     | "Test Case Overivew"  |
      | "Report a Defect"| "Defect Reporter"     |
      | "Defect Overview"| "Defect Overview"     |