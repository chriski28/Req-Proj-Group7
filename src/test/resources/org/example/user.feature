Feature: Client Account Management
  In order to charge my electric vehicle at charging stations
  As a client
  I want to create an account and top up my balance

  # Scenarios for Creating a Client Account

  Scenario: Successfully create a new client account
    When the client provides the following details to create an account:
      | name        | username     | iban                 | cardholderName |
      | John Smith  | johnsmith123 | AT611904300234573201 | John Smith     |
    Then the client account should be successfully created
    And the client should have a starting balance of 0

    #error case

  Scenario: Fail to create an account with missing details
    When the client attempts to create an account with incomplete details:
      | name        | username | iban                 | cardholderName |
      | John Smith  |          | AT611904300234573201 | John Smith     |
    Then the system should display an error message "Please complete all required fields"
    And the account should not be created

  # Scenarios for Topping Up a Client Account

  Scenario: Successfully top up the client account
    Given a client exists with clientId "10000000" and an initial balance of 20.00
    When the client tops up their account with 50.00 on "2024-10-21 10:30:00"
    Then the client's balance should be 70.00
    And a TopUpItem should be created with the amount of 50.00
    And the top-up date should be "2024-10-21 10:30:00"
