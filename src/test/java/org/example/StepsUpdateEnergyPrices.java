package org.example;
/*
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class StepsUpdateEnergyPrices {

    private final List<ChargingSite> chargingSites = new ArrayList<>();
    private ChargingSite currentSite;

    // Helper class to represent a charging site
    public static class ChargingSite {
        String name;
        double acEnergyPrice;
        double dcEnergyPrice;
    }

    // Step: Ensure that a site exists
    @Given("a site {string} exists")
    public void aSiteExists(String siteName) {
        currentSite = chargingSites.stream().filter(site -> site.name.equals(siteName)).findFirst().orElse(null);  // Find the site in the list
        if (currentSite == null) {
            currentSite = new ChargingSite();
            currentSite.name = siteName;
            chargingSites.add(currentSite);  // Add the site if it does not exist
        }
        System.out.println("Ensured the site " + siteName + " exists.");
    }

    // Step: Set the current AC energy price
    @And("the current energy price for AC is {double} EUR per kWh")
    public void theCurrentEnergyPriceForACIs(double price) {
        currentSite.acEnergyPrice = price;
        System.out.println("Set current AC energy price to: " + price + " EUR per kWh");
    }

    // Step: Update the AC energy price
    @When("I update the energy price for AC to {double} EUR per kWh")
    public void iUpdateTheEnergyPriceForACTo(double newPrice) {
        currentSite.acEnergyPrice = newPrice;
        System.out.println("Updated AC energy price to: " + newPrice + " EUR per kWh");
    }

    // Step: Verify the AC energy price
    @Then("the energy price for AC should be {double} EUR per kWh")
    public void theEnergyPriceForACShouldBe(double expectedPrice) {
        if (currentSite.acEnergyPrice == expectedPrice) {
            System.out.println("AC energy price is correct: " + expectedPrice + " EUR per kWh");
        } else {
            throw new AssertionError("Expected AC energy price: " + expectedPrice + ", but got: " + currentSite.acEnergyPrice);
        }
    }

    // Step: Set the current DC energy price
    @And("the current energy price for DC is {double} EUR per kWh")
    public void theCurrentEnergyPriceForDCIs(double price) {
        currentSite.dcEnergyPrice = price;
        System.out.println("Set current DC energy price to: " + price + " EUR per kWh");
    }

    // Step: Update the DC energy price
    @When("I update the energy price for DC to {double} EUR per kWh")
    public void iUpdateTheEnergyPriceForDCTo(double newPrice) {
        currentSite.dcEnergyPrice = newPrice;
        System.out.println("Updated DC energy price to: " + newPrice + " EUR per kWh");
    }

    // Step: Verify the DC energy price
    @Then("the energy price for DC should be {double} EUR per kWh")
    public void theEnergyPriceForDCShouldBe(double expectedPrice) {
        if (currentSite.dcEnergyPrice == expectedPrice) {
            System.out.println("DC energy price is correct: " + expectedPrice + " EUR per kWh");
        } else {
            throw new AssertionError("Expected DC energy price: " + expectedPrice + ", but got: " + currentSite.dcEnergyPrice);
        }
    }
}

 */
