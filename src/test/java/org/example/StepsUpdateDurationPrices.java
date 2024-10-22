package org.example;
/*

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class StepsUpdateDurationPrices {

    private final List<Site> chargingSites = new ArrayList<>();
    private Site currentSite;

    @Given("I manage the network of charging sites")
    public void iManageTheNetworkOfChargingSites() {
        chargingSites.clear(); // Clear any existing sites
    }

    @And("a site named {string} is available")
    public void aSiteNamedIsAvailable(String siteName) {
        currentSite = new Site(siteName, "");
        chargingSites.add(currentSite);
    }

    @And("the address for site {string} is set to {string}")
    public void theAddressForSiteIsSetTo(String siteName, String address) {
        for (Site site : chargingSites) {
            if (site.getName().equals(siteName)) {
                site.setAddress(address);
                currentSite = site;
                break;
            }
        }
    }

    @And("the initial AC duration price for {string} is {double} EUR")
    public void theInitialACDurationPriceForIs(String siteName, double price) {
        for (Site site : chargingSites) {
            if (site.getName().equals(siteName)) {
                site.setDurationPriceAC(price);
                currentSite = site;
                break;
            }
        }
    }

    @And("the initial DC duration price for {string} is {double} EUR")
    public void theInitialDCDurationPriceForIs(String siteName, double price) {
        for (Site site : chargingSites) {
            if (site.getName().equals(siteName)) {
                site.setDurationPriceDC(price);
                currentSite = site;
                break;
            }
        }
    }

    @When("I update the AC duration price to {double} EUR")
    public void iUpdateTheACDurationPriceTo(double newPrice) {
        if (currentSite != null) {
            currentSite.setDurationPriceAC(newPrice);
        }
    }

    @Then("the AC duration price should be {double} EUR")
    public void theACDurationPriceShouldBe(double expectedPrice) {
        if (currentSite != null && currentSite.getDurationPriceAC() == expectedPrice) {
            System.out.println(expectedPrice);
        } else {
            System.out.println(currentSite != null ? currentSite.getDurationPriceAC() : 0.0);
        }
    }

    @When("I update the DC duration price to {double} EUR")
    public void iUpdateTheDCDurationPriceTo(double newPrice) {
        if (currentSite != null) {
            currentSite.setDurationPriceDC(newPrice);
        }
    }

    @Then("the DC duration price should be {double} EUR")
    public void theDCDurationPriceShouldBe(double expectedPrice) {
        if (currentSite != null && currentSite.getDurationPriceDC() == expectedPrice) {
            System.out.println(expectedPrice);
        } else {
            System.out.println(currentSite != null ? currentSite.getDurationPriceDC() : 0.0);
        }
    }
}

 */
