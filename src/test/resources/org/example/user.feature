###########Create Client##########
Feature: Create Client Account
  In order to charge my vehicle at various sites
  As a client
  I want to create a new account for accessing the charging services


  Scenario: Successfully create a new client account
    When the client provides the following details to create an account:
      | name        | username     | iban                 | cardholderName |
      | John Smith  | johnsmith123 | AT611904300234573201 | John Smith     |
    Then the client account should be successfully created
    And the client should have a starting balance of 0

  Scenario: Fail to create an account with missing details
    When the client attempts to create an account with incomplete details:
      | name        | username | iban                 | cardholderName |
      | John Smith  |          | AT611904300234573201 | John Smith     |
    Then the system should display an error message "Please complete all required fields"
    And the account should not be created

