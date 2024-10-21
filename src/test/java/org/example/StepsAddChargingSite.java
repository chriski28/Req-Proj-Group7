package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class StepsAddChargingSite {

    private final List<ChargingSite> chargingSites = new ArrayList<>();
    private ChargingSite currentSite;

    // Helper class to represent a charging site
    public static class ChargingSite {
        String name;
        String address;
    }

    // Step: Managing multiple charging sites
    @Given("I am managing multiple charging sites")
    public void iAmManagingMultipleChargingSites() {
        System.out.println("Managing multiple charging sites...");
    }

    // Step: Adding a new charging site
    @When("I add a new site with the name {string}")
    public void iAddANewSiteWithTheName(String siteName) {
        currentSite = new ChargingSite();
        currentSite.name = siteName;
        chargingSites.add(currentSite);  // Add the site to the list
        System.out.println("Added new site: " + siteName);
    }

    // Step: Setting the address for the charging site
    @And("the address is {string}")
    public void theAddressIs(String address) {
        currentSite.address = address;
        System.out.println("Set address to: " + address);
    }

    // Step: Verify that the new site is added to the list
    @Then("the site {string} should be added to the list of sites")
    public void theSiteShouldBeAddedToTheListOfSites(String siteName) {
        boolean found = chargingSites.stream().anyMatch(site -> site.name.equals(siteName));  // Check if the site exists
        if (found) {
            System.out.println("Site " + siteName + " is added to the list.");
        } else {
            throw new AssertionError("Site " + siteName + " was not found in the list.");
        }
    }

    // Step: Verify that the address of the site is correct
    @And("its address should be {string}")
    public void itsAddressShouldBe(String address) {
        if (currentSite.address.equals(address)) {
            System.out.println("The address is correct: " + address);
        } else {
            throw new AssertionError("Expected address: " + address + ", but got: " + currentSite.address);
        }
    }
}


