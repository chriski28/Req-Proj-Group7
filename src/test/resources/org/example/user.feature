###########Create Client##########
Feature: Create Client Account
  In order to charge my vehicle at various sites
  As a client
  I want to create a new account for accessing the charging services

  Background:
    Given the system is running

  Scenario: Successfully create a new client account
    When the client provides the following details to create an account:
      | name        | username     | iban                 | cardholderName |
      | John Smith  | johnsmith123 | AT611904300234573201 | John Smith     |
    Then the client account should be successfully created
    And the client should have a starting balance of 0

    ###########Error Cases###################

  Scenario: Fail to create an account with missing details
    When the client attempts to create an account with incomplete details:
      | name        | username | iban                 | cardholderName |
      | John Smith  |          | AT611904300234573201 | John Smith     |
    Then the system should display an error message "Please complete all required fields"
    And the account should not be created

  Scenario: Duplicate username during account creation
    Given an account already exists with username "johnsmith123"
    When the client creates an account with username "johnsmith123"
    Then the system should display an error message "Username is already in use"
    And the account should not be created

    ############Top Up Client###################
Feature: Top Up Client Account
  In order to charge my electric vehicle at various charging stations
  As a registered user
  I want to top up my account balance and view my top-up history


  Background: ##delete first line system is running
    Given the system is running for top-up functionality
    And a user exists with userId "10000000" and an initial balance of 20.00
    And the user has the following payment data:
      | IBAN                 | cardholderName |
      | AT611904300234573201 | John Smith     |

  Scenario: Successfully top up the client account
    When the user with userId "10000000" tops up their account with an amount of 50.00
    Then the user's account balance should be 70.00
    And a new TopUpItem should be created in the topUpList
    And the TopUpItem should record the amount of 50.00

  Scenario: View top-up list for a client account
    Given the user with userId "10000000" has topped up their account with the following amounts:
      | Amount |
      | 20.00  |
      | 50.00  |
    When the user views their top-up list
    Then the topUpList should contain the following amounts:
      | Amount |
      | 20.00  |
      | 50.00  |

    ##############Use Charger#####################
Feature: Charging an Electric Vehicle
  In order to charge my electric vehicle within my prepaid balance
  As a registered client
  I want to start and complete a charging session with the correct amount deducted from my balance

  Background:
    Given the charging system is initialized
    And a site exists with name "Vienna Station" and address "Wiedner Gürtel 34"
    And the site has the following prices:
      | energyPriceAC | energyPriceDC | durationPriceAC | durationPriceDC |
      | 0.20          | 0.30          | 0.05            | 0.07           |
    And a charger of type "AC" exists at "Vienna Station" with status "Operational Free"
    And a client exists with clientId "100000000" and the following details:
      | name        | username     | iban                 | cardholderName |
      | John Smith  | johnsmith123 | AT611904300234573201 | John Smith     |
    And the client has an initial balance of 100.00

  Scenario: Charging session within the client's balance
    When the client starts a charging session for 60 minutes
    Then the client's balance should be reduced to 95.6
    And the charger status should be updated to "Operational Busy"

    #####Error Cases######

  Scenario: Insufficient balance to start charging
    When the user attempts to start a charging session
    Then the system should display a message "Insufficient balance to start charging"
    And the user's balance should remain at 0.00
    And the charger status should remain "Operational Free"





