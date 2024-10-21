package org.example;

import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.User;

import java.util.ArrayList;
import java.util.List;

public class StepUser {
    private List<User> clientDatabase = new ArrayList<>();
    User currentClient;
    String errorMessage;

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
}
