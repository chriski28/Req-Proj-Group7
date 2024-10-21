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
        @Given("a client exists with clientId {string} and an initial balance of {double}")
        public void aClientExistsWithClientIdAndAnInitialBalanceOf(String clientId, Double initialBalance) {
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

        @Then("the client's balance should be {double}")
        public void theClientSBalanceShouldBe(Double expectedBalance) {
            assertEquals(expectedBalance, currentClient.getBalance(), "Client's balance is incorrect.");
        }

        @And("a TopUpItem should be created with the amount of {double}")
        public void aTopUpItemShouldBeCreatedWithTheAmountOf(Double expectedAmount) {
            TopUpItem lastTopUp = topUpItems.get(topUpItems.size() - 1);
            assertEquals(expectedAmount, lastTopUp.getAmount(), "TopUp amount is incorrect.");
        }

        @And("the top-up date should be {string}")
        public void theTopUpDateShouldBe(String expectedDate) {
            TopUpItem lastTopUp = topUpItems.get(topUpItems.size() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parsedDate = LocalDateTime.parse(expectedDate, formatter);
            assertEquals(parsedDate, lastTopUp.getDate(), "Top-up date is incorrect.");
        }


}
