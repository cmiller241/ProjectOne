Feature: Matrix

# Declarative Style
  Scenario Outline: Update Defects
    Given The employee is on the login page
    Given The employee types <username> into username input
    Given The employee types <password> into password input
    Given The employee clicks on the login button
    Given The employee clicks on Matrices
    Given A manager or tester has selected a matrix
    When A manager or tester adds defects
    When A manager or tester confirms their changes
    When A manager or tester removes defects
    Then Then the matrix should saved


    Examples:
      | username   | password |
      | "g8tor"      | "chomp!"   |
      | "ryeGuy"     | "coolbeans" |


  Scenario Outline: Update Test Cases
    Given The employee is on the login page
    Given The employee types <username> into username input
    Given The employee types <password> into password input
    Given The employee clicks on the login button
    Given The employee clicks on Matrices
    Given A manager or tester has selected a matrix
    When A manager or tester adds or removes Test Cases
    When A manager or tester confirms their changes
    Then Then the matrix should saved

    Examples:
      | username   | password |
      | "g8tor"      | "chomp!"   |
      | "ryeGuy"     | "coolbeans" |


  Scenario Outline: Create a New Matrix
    Given The manager is logged in as a manager
    Given The manager is on the home page
    Then A manager can pull up a form to make a new matrix
    When A manager creates a title for a matrix
    When A manager adds requirements to a matrix
    When A manager saves a matrix
    Given The employee is on the login page
    When  The employee types <username> into username input
    When The employee types <password> into password input
    When The employee clicks on the login button
    Then The matrix should be visible for all testers and managers

    Examples:
      | username   | password |
      | "g8tor"      | "chomp!"   |
      | "ryeGuy"     | "coolbeans" |
      | "cavalier89" | "alucard"   |

