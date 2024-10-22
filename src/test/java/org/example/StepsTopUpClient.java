/*
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class StepsTopUpClient {

    private List<User> clientDatabase = new ArrayList<>();
    private User currentClient;
    private String lastErrorMessage;

    // Step definition for initializing the system specifically for top-up functionality
    @Given("the system is running for top-up functionality")
    public void the_system_is_running_for_top_up() {
        clientDatabase = new ArrayList<>();
        lastErrorMessage = null;
    }

    // Step definition for adding a user with userId and initial balance
    @And("a user exists with userId {string} and an initial balance of {double}")
    public void a_user_exists_with_userId_and_initial_balance(String userId, double balance) {
        currentClient = new User();
        currentClient.setUserId(userId);
        currentClient.setBalance(balance);
        clientDatabase.add(currentClient);
    }

    // Step definition for providing user payment data
    @And("the user has the following payment data:")
    public void the_user_has_payment_data(DataTable dataTable) {
        List<List<String>> paymentData = dataTable.asLists(String.class);
        String iban = paymentData.get(1).get(0);
        String cardholderName = paymentData.get(1).get(1);

        User.PaymentData data = currentClient.new PaymentData();
        data.setIban(iban);
        data.setCardholderName(cardholderName);
        currentClient.setPaymentData(data);
    }

    // Step definition for topping up the account
    @When("the user with userId {string} tops up their account with an amount of {double}")
    public void the_user_with_userId_tops_up_their_account(String userId, double amount) {
        User user = clientDatabase.stream()
                .filter(client -> client.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        if (user != null) {
            TopUpItem topUp = new TopUpItem(amount);
            user.addTopUp(topUp);
        } else {
            lastErrorMessage = "User not found";
        }
    }

    // Step definition for verifying the account balance
    @Then("the user's account balance should be {double}")
    public void the_user_account_balance_should_be(double expectedBalance) {
        assertNotNull(currentClient, "User should exist");
        assertEquals(expectedBalance, currentClient.getBalance(), "Balance should match the expected value");
    }

    // Step definition for checking if a new TopUpItem was created
    @And("a new TopUpItem should be created in the topUpList")
    public void a_new_TopUpItem_should_be_created() {
        assertNotNull(currentClient, "User should exist");
        assertFalse(currentClient.getTopUpList().isEmpty(), "Top-up list should not be empty");
    }

    // Step definition for checking the amount recorded in the TopUpItem
    @And("the TopUpItem should record the amount of {double}")
    public void the_TopUpItem_should_record_amount(double amount) {
        TopUpItem lastTopUp = currentClient.getTopUpList().get(currentClient.getTopUpList().size() - 1);
        assertEquals(amount, lastTopUp.getAmount(), "Top-up amount should match the expected value");
    }

    // Step definition for viewing top-up list for a user
    @Given("the user with userId {string} has topped up their account with the following amounts:")
    public void the_user_with_userId_has_topped_up(String userId, DataTable dataTable) {
        User user = clientDatabase.stream()
                .filter(client -> client.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        if (user != null) {
            List<List<String>> rows = dataTable.asLists(String.class);
            for (int i = 1; i < rows.size(); i++) {
                String amountString = rows.get(i).get(0);
                try {
                    double amount = Double.parseDouble(amountString);
                    TopUpItem topUp = new TopUpItem(amount);
                    user.addTopUp(topUp);
                } catch (NumberFormatException e) {
                    lastErrorMessage = "Invalid number format for amount: " + amountString;
                    return;
                }
            }
        } else {
            lastErrorMessage = "User not found";
        }
    }

    // Step definition for viewing the top-up list
    @When("the user views their top-up list")
    public void the_user_views_topUpList() {
        assertNotNull(currentClient.getTopUpList(), "Top-up list should be retrievable");
    }

    // Step definition for verifying the top-up list details
    @Then("the topUpList should contain the following amounts:")
    public void the_topUpList_should_contain(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        List<Double> expectedAmounts = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            expectedAmounts.add(Double.parseDouble(rows.get(i).get(0)));
        }

        List<TopUpItem> topUpList = currentClient.getTopUpList();
        assertEquals(expectedAmounts.size(), topUpList.size(), "The number of top-ups should match the expected count");

        for (int i = 0; i < expectedAmounts.size(); i++) {
            assertEquals(expectedAmounts.get(i), topUpList.get(i).getAmount(), "Top-up amount should match");
        }
    }
}

 */
