package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class StepsManageChargers {

    private final List<Charger> chargers = new ArrayList<>();
    private Charger currentCharger;

    // Step: Managing a specific charging site
    @Given("I am managing a charging site {string}")
    public void iAmManagingAChargingSite(String siteName) {
        System.out.println("Managing the charging site: " + siteName);
    }

    // Step: Adding a new charger of type AC or DC
    @When("I add a new charger of type {string} at the site")
    public void iAddANewChargerOfTypeAtTheSite(String type) {
        if (type.equalsIgnoreCase("AC")) {
            currentCharger = new Charger(Charger.Type.AC);
        } else if (type.equalsIgnoreCase("DC")) {
            currentCharger = new Charger(Charger.Type.DC);
        } else {
            throw new IllegalArgumentException("Unknown charger type: " + type);
        }
        chargers.add(currentCharger);
        System.out.println("Added a new charger of type: " + type);
    }

    // Step: Verify the charger is added with the status "operational free"
    @Then("the new charger should be added with the status {string}")
    public void theNewChargerShouldBeAddedWithTheStatus(String expectedStatus) {
        if (!currentCharger.getStatus().toString().equalsIgnoreCase(expectedStatus)) {
            throw new AssertionError("Expected status: " + expectedStatus + ", but got: " + currentCharger.getStatus());
        }
        System.out.println("Charger has the correct status: " + expectedStatus);
    }

    // Step: Verify the type of the charger (AC or DC)
    @And("the type of the charger should be {string}")
    public void theTypeOfTheChargerShouldBe(String expectedType) {
        if (!currentCharger.getType().toString().equalsIgnoreCase(expectedType)) {
            throw new AssertionError("Expected type: " + expectedType + ", but got: " + currentCharger.getType());
        }
        System.out.println("Charger has the correct type: " + expectedType);
    }
}
