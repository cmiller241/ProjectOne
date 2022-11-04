Feature: Login

  Scenario: Login Correct Username Wrong Password
    Given The employee is on the login page
    When The employee types "g8tor" into username input
    When The employee types "chomp!" into password input
    When The employee clicks on the login button
    Then The employee should see an alert saying they have the wrong password

  Scenario: Login Wrong Username
    Given The employee is on the login page
    When The employee types "sicEmDawgs" into username input
    When The employee types "natchamps" into password input
    When The employee clicks on the login button
    Then The employee should see an alert saying no user with that username found