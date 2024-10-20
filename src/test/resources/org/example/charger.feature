Feature: Management of Chargers

  As the owner of a network of charging stations,
  I want to manage the status and type of each charger,
  so that I can ensure the chargers are operational and used correctly.

  Scenario: Adding a new charger of type AC
    Given I am managing a charging site "City Center Site"
    And I am managing a charging site "Suburb Site"
    When I add a new charger of type "AC" at the site
    And I add a new charger of type "DC" at the site
    Then the new charger should be added with the status "operational free"
    And the type of the charger should be "AC"

  Scenario: Adding a new charger of type DC
    Given I am managing a charging site "City Center Site"
    When I add a new charger of type "DC" at the site
    Then the new charger should be added with the status "operational free"
    And the type of the charger should be "DC"


