Feature: InvoiceItem
  In order to learn how much I spend
  As a client
  I want an invoice item to be saved for each transaction

  Background:
    Given a user exists with an initial balance of 20.00
    And the site "Floridsdorf" exists
    And the durationPriceAc at site "Floridsdorf" was changed to 0.30 on "2024-10-20 12:30:00"
    And the energyPriceAc at site "Floridsdorf" was changed to  0.90 on "2024-10-20 12:30:00"

  Scenario:
    Given I am at the AC charger "Floridsfrog3" which is at site "Floridsdorf"
    And I am charging my vehicle for 30 minutes at "2024-10-21 10:00:00"
    Then a new invoiceItem will be created with the following information
      | invoiceID | date               | clientID  | duration | energyConsumed | chargerType | totalPrice |
      | 10000000  | 2024-10-21T10:00   | 10000000  | 30       | 3.5            | AC          | 12.15      |

  Scenario: Display transaction history with both top-ups and invoices
    Given a client with ID "10000000" exists
    And the client has an invoice with the following information
      | invoiceID | date               | clientID  | duration | energyConsumed | chargerType | totalPrice |
      | 10000001  | 2024-10-21 10:00:00| 10000000  | 30       | 3.5            | AC          | 12.15      |
    And the client has a top-up with the following information
      | amount  | date               |
      | 20.00   | 2024-10-19 09:00:00|
    When the client views their transaction history
    Then the transaction history will display the following:
      | amount  | date               | invoiceID | clientID  | duration | energyConsumed | chargerType | totalPrice |
      | 20.00   | 2024-10-19 09:00:00|           |           |          |                 |             |             |
      |         | 2024-10-21 10:00:00| 10000001  | 10000000  | 30       | 3.5            | AC          | 12.15      |
