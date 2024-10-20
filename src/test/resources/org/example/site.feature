Feature: Management of Charging Sites with Energy and Duration Prices


  Background:
    Given I manage the network of charging sites
    And a site named "City Center Site" is available
    And the address for site "City Center Site" is set to "123 Main Street"
    And the initial AC duration price for "City Center Site" is 0.05 EUR
    And the initial DC duration price for "City Center Site" is 0.10 EUR

  Scenario: Adding a new charging site
    Given I am managing multiple charging sites
    When I add a new site with the name "Suburb Site"
    And the address is "456 Lane"
    Then the site "Suburb Site" should be added to the list of sites
    And its address should be "456 Lane"

  Scenario: Adding a new charging site
    Given I am managing multiple charging sites
    When I add a new site with the name "City Center Site"
    And the address is "123 Main Street"
    Then the site "City Center Site" should be added to the list of sites
    And its address should be "123 Main Street"

  Scenario: Updating energy prices for AC
    Given a site "City Center Site" exists
    And the current energy price for AC is 0.30 EUR per kWh
    When I update the energy price for AC to 0.32 EUR per kWh
    Then the energy price for AC should be 0.32 EUR per kWh


  Scenario: Updating energy prices for DC
    Given a site "City Center Site" exists
    And the current energy price for DC is 0.40 EUR per kWh
    When I update the energy price for DC to 0.42 EUR per kWh
    Then the energy price for DC should be 0.42 EUR per kWh

  Scenario: Updating duration price for AC
    When I update the AC duration price to 0.06 EUR
    Then the AC duration price should be 0.06 EUR

  Scenario: Updating duration price for DC
    When I update the DC duration price to 0.12 EUR
    Then the DC duration price should be 0.12 EUR
