package org.example;
/*

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StepsCharging {

    private Site site;
    private Charger charger;
    private User client;

    // Step definition for initializing the charging system
    @Given("the charging system is initialized")
    public void theChargingSystemIsInitialized() {
        // Reset the necessary components for the scenario
        site = null;
        charger = null;
        client = null;
    }

    // Step definition for adding a site with prices
    @And("a site exists with name {string} and address {string}")
    public void aSiteExistsWithNameAndAddress(String name, String address) {
        site = new Site(name, address);
    }

    @And("the site has the following prices:")
    public void theSiteHasTheFollowingPrices(DataTable dataTable) {
        List<List<String>> prices = dataTable.asLists(String.class);
        double energyPriceAC = Double.parseDouble(prices.get(1).get(0));
        double energyPriceDC = Double.parseDouble(prices.get(1).get(1));
        double durationPriceAC = Double.parseDouble(prices.get(1).get(2));
        double durationPriceDC = Double.parseDouble(prices.get(1).get(3));

        site.setEnergyPriceAC(energyPriceAC);
        site.setEnergyPriceDC(energyPriceDC);
        site.setDurationPriceAC(durationPriceAC);
        site.setDurationPriceDC(durationPriceDC);
    }

    // Step definition for adding a charger to the site
    @And("a charger of type {string} exists at {string} with status {string}")
    public void aChargerOfTypeExistsAtWithStatus(String type, String siteName, String status) {
        assertNotNull(site, "Site must be initialized before adding a charger.");
        charger = new Charger(type.equals("AC") ? Charger.Type.AC : Charger.Type.DC);
        charger.setStatus(status.equals("Operational Free") ? Charger.Status.OPERATIONAL_FREE : Charger.Status.OUT_OF_ORDER);
    }

    // Step definition for adding a client
    @And("a client exists with clientId {string} and the following details:")
    public void aClientExistsWithClientIdAndTheFollowingDetails(String clientId, DataTable dataTable) {
        List<List<String>> clientData = dataTable.asLists(String.class);

        client = new User();
        client.setUserId(clientId);
        client.setName(clientData.get(1).get(0));
        client.setUsername(clientData.get(1).get(1));

        User.PaymentData paymentData = client.new PaymentData();
        paymentData.setIban(clientData.get(1).get(2));
        paymentData.setCardholderName(clientData.get(1).get(3));
        client.setPaymentData(paymentData);
    }

    // Step definition for setting the initial balance of the client
    @And("the client has an initial balance of {double}")
    public void theClientHasAnInitialBalanceOf(double balance) {
        client.setBalance(balance);
    }

    // Step definition for starting a charging session
    @When("the client starts a charging session for {int} minutes")
    public void theClientStartsAChargingSessionForMinutes(int durationMinutes) {
        assertNotNull(client, "Client must be initialized before starting a charging session.");
        assertNotNull(charger, "Charger must be initialized before starting a charging session.");
        assertNotNull(site, "Site must be initialized before starting a charging session.");

        String result = client.charge(charger, site, durationMinutes);
        assertEquals("Charging session started successfully.", result, "Charging session should start successfully if the balance is sufficient.");
    }

    // Step definition for verifying the client's balance after charging
    @Then("the client's balance should be reduced to {double}")
    public void theClientSBalanceShouldBeReducedTo(double expectedBalance) {
        assertEquals(expectedBalance, client.getBalance(), 0.01, "The client's balance should be reduced correctly.");
    }

    // Step definition for verifying the charger's status
    @And("the charger status should be updated to {string}")
    public void theChargerStatusShouldBeUpdatedTo(String expectedStatus) {
        assertEquals(expectedStatus, charger.getStatus().toString(), "The charger status should be updated correctly.");
    }

    // Step definition for an attempt to start a charging session with insufficient balance
    @When("the user attempts to start a charging session")
    public void theUserAttemptsToStartAChargingSession() {
        assertNotNull(client, "Client must be initialized before attempting a charging session.");
        assertNotNull(charger, "Charger must be initialized before attempting a charging session.");
        assertNotNull(site, "Site must be initialized before attempting a charging session.");

        // Set the client's balance to 0 to simulate an insufficient balance scenario
        client.setBalance(0.00);

        String result = client.charge(charger, site, 60);
        assertEquals("Insufficient balance to start charging", result, "The system should display the correct error message when the balance is insufficient.");
    }

    // Step definition for verifying the error message when the balance is insufficient
    @Then("the system should display a message {string}")
    public void theSystemShouldDisplayAMessage(String expectedMessage) {
        String result = client.charge(charger, site, 60);
        assertEquals(expectedMessage, result, "The system should display the correct error message when the balance is insufficient.");
    }

    // Step definition for verifying the user's balance remains unchanged
    @And("the user's balance should remain at {double}")
    public void theUserSBalanceShouldRemainAt(double expectedBalance) {
        assertEquals(expectedBalance, client.getBalance(), 0.01, "The user's balance should remain unchanged if the charging session is not started.");
    }

    // Step definition for verifying the charger status remains unchanged
    @And("the charger status should remain {string}")
    public void theChargerStatusShouldRemain(String expectedStatus) {
        assertEquals(expectedStatus, charger.getStatus().toString(), "The charger status should remain unchanged if the charging session is not started.");
    }
}
*/