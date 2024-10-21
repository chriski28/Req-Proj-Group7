package org.example;

import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TopUpItem;
import org.example.User;
import io.cucumber.datatable.DataTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StepUser {
    private List<User> clientDatabase = new ArrayList<>();
    User currentClient;
    String errorMessage;
    List<TopUpItem> topUpItems = new ArrayList<>();

    private Site currentSite;
    private Charger currentCharger;
    private double energyPriceAC;
    private double durationPriceAC;


    @When("the client provides the following details to create an account:")
    public void the_client_provides_the_following_details_to_create_an_account(DataTable dataTable) {
        List<List<String>> details = dataTable.asLists(String.class);
        String name = details.get(1).get(0);
        String username = details.get(1).get(1);
        String iban = details.get(1).get(2);
        String cardholderName = details.get(1).get(3);

        // Check if any of the details are null or empty and return early if invalid
        if (name == null || username == null || iban == null || cardholderName == null ) {
            fail("One or more account details are missing. Please provide valid data.");
            return;
        }

        // Creating a new User object using the constructor
        currentClient = new User(name, username, iban, cardholderName);

        // Add the client to the database
        clientDatabase.add(currentClient);
    }

    @Then("the client account should be successfully created")
    public void the_client_account_should_be_successfully_created() {
        assertTrue(clientDatabase.contains(currentClient), "Client account was not created in the database.");
    }

    @Then("the client should have a starting balance of {double}")
    public void the_client_should_have_a_starting_balance_of(Double expectedBalance) {
        assertEquals(expectedBalance, currentClient.getBalance(), "Starting balance is incorrect.");
    }

    // Step definition for incomplete account creation
    @When("the client attempts to create an account with incomplete details:")
    public void theClientAttemptsToCreateAnAccountWithIncompleteDetails(DataTable dataTable) {
        List<List<String>> details = dataTable.asLists(String.class);
        String name = details.get(1).get(0);
        String username = details.get(1).get(1);
        String iban = details.get(1).get(2);
        String cardholderName = details.get(1).get(3);

        // Check if any of the details are missing or empty
        if (name == null || username == null || iban == null || cardholderName == null ){
            errorMessage = "Please complete all required fields";
        } else {
            currentClient = new User(name, username, iban, cardholderName);
            clientDatabase.add(currentClient);
        }
    }

    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayAnErrorMessage(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage, "Error message mismatch");
    }

    @And("the account should not be created")
    public void theAccountShouldNotBeCreated() {
        assertFalse(clientDatabase.contains(currentClient), "Account should not be created in the database.");
    }
    ///////////////////////Top Up Client/////////////////////////////////
        @Given("a client exists with clientId {string} and a balance of {double}")
        public void aClientExistsWithClientIdAndABalanceOf(String clientId, Double initialBalance) {
            currentClient = new User();
            currentClient.setUserId(clientId);
            currentClient.setBalance(initialBalance);
        }

        @When("the client tops up their account with {double} on {string}")
        public void theClientTopsUpTheirAccountWithOn(Double amount, String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime topUpDate = LocalDateTime.parse(date, formatter);
            TopUpItem topUpItem = new TopUpItem(amount, topUpDate);
            currentClient.addTopUp(topUpItem);
            topUpItems.add(topUpItem);
        }

        @Then("the new client's balance should be {double}")
        public void theNewClientSBalanceShouldBe(Double expectedBalance) {
            assertEquals(expectedBalance, currentClient.getBalance(), "Client's balance is incorrect.");
        }

        @And("a TopUpItem should be created with the amount of {double}")
        public void aTopUpItemShouldBeCreatedWithTheAmountOf(Double expectedAmount) {
            TopUpItem lastTopUp = topUpItems.get(topUpItems.size() - 1); //statt getLast()
            assertEquals(expectedAmount, lastTopUp.getAmount(), "TopUp amount is incorrect.");
        }

        @And("the top-up date should be {string}")
        public void theTopUpDateShouldBe(String expectedDate) {
            TopUpItem lastTopUp = topUpItems.get(topUpItems.size() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parsedDate = LocalDateTime.parse(expectedDate, formatter);
            assertEquals(parsedDate, lastTopUp.getDate(), "Top-up date is incorrect.");
        }

////////////charging station

        @Given("a client at an AC charger at the site {string}")
        public void aClientAtAnACChargerAtTheSite(String siteName) {
            // Initialize Site and Charger
            currentSite = new Site(siteName);
            currentCharger = new Charger(currentSite, Charger.Type.AC);
        }

        @And("the site has the following prices as of {string}:")
        public void theSiteHasTheFollowingPricesAsOf(String date, DataTable pricesTable) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime priceDate = LocalDateTime.parse(date, formatter);

            // Parse prices from the table
            List<List<String>> prices = pricesTable.asLists(String.class);
            energyPriceAC = Double.parseDouble(prices.get(1).get(0));  // AC Energy Price
            durationPriceAC = Double.parseDouble(prices.get(1).get(1));  // AC Duration Price

            // Set the prices for the current site
            currentSite.setEnergyPriceAC(priceDate, energyPriceAC);
            currentSite.setDurationPriceAC(priceDate, durationPriceAC);
        }

        @Given("a client exists with clientId {string} and an initial balance of {double}")
        public void aClientExistsWithClientIdAndAnInitialBalanceOf(String clientId, Double balance) {
            // Create and initialize the client
            currentClient = new User();
            currentClient.setUserId(clientId);
            currentClient.setBalance(balance);
        }

        @When("the client starts a {int}-minute charging session at {string}")
        public void theClientStartsAMinuteChargingSessionAt(int durationMinutes, String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime sessionDate = LocalDateTime.parse(date, formatter);

            // Use the charge function from the User class to handle the charging session
            String result = currentClient.charge(currentCharger, durationMinutes, sessionDate);

            // Assert that the charging session started successfully
            assertEquals("Charging session started successfully.", result, "Charging session failed.");
        }

        @Then("the client's balance should be {double}")
        public void theClientSBalanceShouldBe(Double expectedBalance) {
            // Check that the client's balance is correctly updated
            assertEquals(expectedBalance, currentClient.getBalance(), "Client's balance is incorrect.");
        }

        @And("the charger status should be {string}")
        public void theChargerStatusShouldBe(String expectedStatus) {
            // Check that the charger status is updated correctly
            assertEquals(expectedStatus, currentCharger.getStatus().toString(), "Charger status is incorrect.");
        }


        @Given("a client is at the {string} site with an AC charger available")
        public void aClientIsAtTheSiteWithAnACChargerAvailable(String siteName) {
            currentSite = new Site(siteName);
            currentCharger = new Charger(currentSite, Charger.Type.AC);
        }

        @And("the client has clientId {string} and a balance of {double}")
        public void theClientHasClientIdAndABalanceOf(String clientId, Double balance) {
            currentClient = new User();
            currentClient.setUserId(clientId);
            currentClient.setBalance(balance);
        }

        @And("the site has these prices as of {string}:")
        public void theSiteHasThesePricesAsOf(String date, DataTable pricesTable) {
            List<List<String>> prices = pricesTable.asLists(String.class);
            energyPriceAC = Double.parseDouble(prices.get(1).get(0));
            durationPriceAC = Double.parseDouble(prices.get(1).get(1));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime priceDate = LocalDateTime.parse(date, formatter);
            currentSite.setEnergyPriceAC(priceDate, energyPriceAC);
            currentSite.setDurationPriceAC(priceDate, durationPriceAC);
        }

        @When("the client attempts to start a {int}-minute charging session at {string}")
        public void theClientAttemptsToStartAMinuteChargingSessionAt(int minutes, String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime sessionDate = LocalDateTime.parse(date, formatter);
            errorMessage = currentClient.charge(currentCharger, minutes, sessionDate);
        }

        @Then("the system should display a message {string}")
        public void theSystemShouldDisplayAMessage(String expectedMessage) {
            assertEquals(expectedMessage, errorMessage, "Error message mismatch.");
        }

        @And("the client's balance will stay at {double}")
        public void theClientSBalanceWillStayAt(Double expectedBalance) {
            assertEquals(expectedBalance, currentClient.getBalance(), "Balance mismatch.");
        }

        @And("the charger status will remain {string}")
        public void theChargerStatusWillRemain(String expectedStatus) {
            assertEquals(expectedStatus, currentCharger.getStatus().toString(), "Charger status mismatch.");
        }


}
