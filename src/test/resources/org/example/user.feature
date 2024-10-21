Feature: Client Account and Charging Management
  In order to charge my electric vehicle at charging stations
  As a client
  I want to create an account, top up my balance, use a charger for charging sessions, and delete my account when needed

  # Scenarios for Creating a Client Account

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

  # Scenarios for Topping Up a Client Account

  Scenario: Successfully top up the client account
    Given a client exists with clientId "10000000" and an initial balance of 20.00
    When the client tops up their account with 50.00 on "2024-10-21 10:30:00"
    Then the client's balance should be 70.00
    And a TopUpItem should be created with the amount of 50.00
    And the top-up date should be "2024-10-21 10:30:00"

  # Scenarios for Using a Charger

  Scenario: Charging session within the client's balance
    Given a client at an AC charger at the site "Floridsdorf"
    And the site has the following prices as of "2024-10-21 10:30:00":
      | energyPriceAC | durationPriceAC |
      | 0.20          | 0.05            |
    And a client exists with clientId "10000000" and a balance of 100.00
    When the client starts a 60-minute charging session at "2024-10-21 10:30:00"
    Then the new client's balance should be 95.60
    And the charger status should be "Operational Busy"

  Scenario: Insufficient balance to start charging
    Given a client is at the "Floridsdorf" site with an AC charger available
    And the client has clientId "10000000" and a balance of 0.00
    And the site has these prices as of "2024-10-21 10:30:00":
      | energyPriceAC | durationPriceAC |
      | 0.20          | 0.05            |
    When the client attempts to start a 60-minute charging session at "2024-10-21 10:30:00"
    Then the system should display a message "Insufficient balance to start charging"
    And the client's balance will stay at 0.00
    And the charger status will remain "Operational Free"

     # Scenario for Account Deletion
  Scenario: Successfully delete my account
    Given a client with userId "10000000" exists
    When the client requests to delete their account
    Then the account should be deleted



