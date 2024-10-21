/*
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StepsCreateClientAccount {

    private List<User> clientDatabase = new ArrayList<>();
    private User currentClient;
    private String lastErrorMessage;

    // Step definition for initializing the system
    @Given("the system is running")
    public void the_system_is_running() {
        // Reset the client database for each scenario
        clientDatabase = new ArrayList<>();
        lastErrorMessage = null;
    }

    // Step definition for providing client details
    @When("the client provides the following details to create an account:")
    public void the_client_provides_the_following_details_to_create_an_account(DataTable dataTable) {
        List<List<String>> clientData = dataTable.asLists(String.class);

        String name = clientData.get(1).get(0); // Retrieve 'name' value
        String username = clientData.get(1).get(1); // Retrieve 'username' value
        String iban = clientData.get(1).get(2); // Retrieve 'iban' value
        String cardholderName = clientData.get(1).get(3); // Retrieve 'cardholderName' value

        // Check if any details are missing
        if (name == null || name.isEmpty() || username == null || username.isEmpty() || iban == null || iban.isEmpty() || cardholderName == null || cardholderName.isEmpty()) {
            lastErrorMessage = "Please complete all required fields";
            return;
        }

        // Create new client
        currentClient = new User();
        currentClient.setName(name);
        currentClient.setUsername(username);

        // Set up PaymentData
        User.PaymentData paymentData = currentClient.new PaymentData();
        paymentData.setIban(iban);
        paymentData.setCardholderName(cardholderName);
        currentClient.setPaymentData(paymentData);

        // Set initial balance
        currentClient.setBalance(0.0);

        // Store client in the list
        clientDatabase.add(currentClient);
    }

    // Step definition for successfully creating an account
    @Then("the client account should be successfully created")
    public void the_client_account_should_be_successfully_created() {
        assertNotNull(currentClient, "Client should be created");
        assertTrue(clientDatabase.contains(currentClient), "Client should be in the database");
    }

    // Step definition for checking starting balance
    @Then("the client should have a starting balance of {double}")
    public void the_client_should_have_a_starting_balance_of(Double expectedBalance) {
        assertEquals(expectedBalance, currentClient.getBalance(), "The client should have the correct starting balance");
    }

    // Step definition for creating an account with missing details
    @When("the client attempts to create an account with incomplete details:")
    public void the_client_attempts_to_create_an_account_with_incomplete_details(DataTable dataTable) {
        List<List<String>> clientData = dataTable.asLists(String.class);

        String name = clientData.get(1).get(0);
        String username = clientData.get(1).get(1);
        String iban = clientData.get(1).get(2);
        String cardholderName = clientData.get(1).get(3);

        // Check for missing details
        if (name == null || name.isEmpty() || username == null || username.isEmpty() || iban == null || iban.isEmpty() || cardholderName == null || cardholderName.isEmpty()) {
            lastErrorMessage = "Please complete all required fields";
        } else {
            currentClient = new User();
            currentClient.setName(name);
            currentClient.setUsername(username);

            User.PaymentData paymentData = currentClient.new PaymentData();
            paymentData.setIban(iban);
            paymentData.setCardholderName(cardholderName);
            currentClient.setPaymentData(paymentData);
        }
    }

    // Step definition for displaying error message on missing details
    @Then("the system should display an error message {string}")
    public void the_system_should_display_an_error_message(String expectedErrorMessage) {
        assertEquals(expectedErrorMessage, lastErrorMessage, "Error message should match");
    }

    // Step definition for checking if account was not created
    @Then("the account should not be created")
    public void the_account_should_not_be_created() {
        assertNull(currentClient, "Client account should not be created due to missing details");
    }

    // Step definition for checking existing account
    @Given("an account already exists with username {string}")
    public void an_account_already_exists_with_username(String username) {
        User existingClient = new User();
        existingClient.setUsername(username);
        clientDatabase.add(existingClient);
    }

    // Step definition for duplicate account creation attempt
    @When("the client creates an account with username {string}")
    public void the_client_creates_an_account_with_username(String username) {
        boolean usernameExists = clientDatabase.stream()
                .anyMatch(client -> client.getUsername().equals(username));

        if (usernameExists) {
            lastErrorMessage = "Username is already in use";
        } else {
            currentClient = new User();
            currentClient.setUsername(username);
            clientDatabase.add(currentClient);
        }
    }
}


 */

