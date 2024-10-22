package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StepsInvoice {

    List<Site> sites = new LinkedList<Site>();
    List<Charger> chargers = new LinkedList<Charger>();
    List<User> clients = new LinkedList<User>();
    List<InvoiceItem> invoiceItems = new LinkedList<InvoiceItem>();

    private User user;
    private List<Object> allTransactions = new LinkedList<>();

    //Background

    @Given("a user exists with an initial balance of {double}")
    public void a_user_exists_with_an_initial_balance_of(Double balance) {
        clients.add(new User());
        //Get last müssen wir echt ändern wenn wir eine Lösung finden haha
        clients.get(0).setBalance(balance);

    }

    @And("the site {string} exists")
    public void the_site_exists(String siteName){
        sites.add(new Site(siteName));
    }

    @And("the durationPriceAc at site {string} was changed to {double} on {string}")
    public void the_duration_price_ac_at_site_was_changed_to_on(String site,
                                                                double durationPriceAC,
                                                                String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.parse(date, formatter);
        sites.get(0).setDurationPriceAC(temp,durationPriceAC);

    }

    @And("the energyPriceAc at site {string} was changed to  {double} on {string}")
    public void the_energy_price_ac_at_site_was_changed_to_on(String site,
                                                              double energyPriceAC,
                                                              String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.parse(date, formatter);
        sites.get(0).setEnergyPriceAC(temp,energyPriceAC);
    }

    @Given("I am at the AC charger {string} which is at site {string}")
    public void i_am_at_the_ac_charger_which_is_at_site(String chargerName, String siteName) {

        chargers.add(new Charger(sites.get(0), Charger.Type.AC));
    }

    @And("I am charging my vehicle for {int} minutes at {string}")
    public void i_am_charging_my_vehicle_for_minutes_at(int duration, String date) {
        //schau zb hier werden die variablen im methodennamen weggelassen übergeben wir noch irgendwo einen String?

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.parse(date, formatter);

        InvoiceItem currentInvoiceItem = new InvoiceItem(clients.get(0),  temp,chargers.get(0), duration);
        invoiceItems.add(currentInvoiceItem);
    }
    @Then("a new invoiceItem will be created with the following information")
    public void a_new_invoice_item_will_be_created_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> attributes = dataTable.asLists(String.class);

        String expectedOutput = "InvoiceItem{" +
                "type=Invoice" +  // Add this line to match the actual output
                ", invoiceID=" + attributes.get(1).get(0) +
                ", date=" + attributes.get(1).get(1) +
                ", client=" + attributes.get(1).get(2) +
                ", duration=" + attributes.get(1).get(3) + "minutes" +
                ", energyConsumed=" + attributes.get(1).get(4) + "kWh" +
                ", chargerType=" + attributes.get(1).get(5) +
                ", totalPrice=" + attributes.get(1).get(6) +
                '}';

        assertEquals(expectedOutput, invoiceItems.get(0).toString(), "blablab");
    }

    @Given("a client with ID {string} exists")
    public void aClientWithIDExists(String userId) {
        // Create a new user with the provided ID
        user = new User();
        user.setUserId(userId);
        clients.add(user);
    }

    @And("the client has an invoice with the following information")
    public void theClientHasAnInvoiceWithTheFollowingInformation(DataTable dataTable) {
        // Extract invoice details from the DataTable
        List<List<String>> invoiceDetails = dataTable.asLists(String.class);

        String invoiceID = invoiceDetails.get(1).get(0);
        String dateStr = invoiceDetails.get(1).get(1);
        String clientID = invoiceDetails.get(1).get(2);
        int duration = Integer.parseInt(invoiceDetails.get(1).get(3));
        double energyConsumed = Double.parseDouble(invoiceDetails.get(1).get(4));
        String chargerType = invoiceDetails.get(1).get(5);  // Should be "AC"
        double totalPrice = Double.parseDouble(invoiceDetails.get(1).get(6));

        // Parse the date using the updated format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime invoiceDate = LocalDateTime.parse(dateStr, formatter);

        // Reuse the existing site from the background ("Floridsdorf")
        Site site = sites.get(0);  // Assuming it's the first site in the list

        // Create the Charger object associated with the site and set type to AC
        Charger charger = new Charger(site, Charger.Type.AC);

        // Create the InvoiceItem with the correct charger
        InvoiceItem invoiceItem = new InvoiceItem(user, invoiceDate, charger, duration);

        // Manually set the invoiceID if required
        invoiceItem.setInvoiceID(invoiceID);  // This line allows manual setting of the invoice ID if needed.

        user.addInvoiceItem(invoiceItem);
    }


    @And("the client has a top-up with the following information")
    public void theClientHasATopUpWithTheFollowingInformation(DataTable dataTable) {
        // Extract top-up details from the DataTable
        List<List<String>> topUpDetails = dataTable.asLists(String.class);

        double amount = Double.parseDouble(topUpDetails.get(1).get(0));
        String dateStr = topUpDetails.get(1).get(1);

        // Parse the date using the updated format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime topUpDate = LocalDateTime.parse(dateStr, formatter);

        // Create a TopUpItem and add it to the user's account
        TopUpItem topUpItem = new TopUpItem(amount, topUpDate);
        user.addTopUp(topUpItem);
    }

    @When("the client views their transaction history")
    public void theClientViewsTheirTransactionHistory() {
        // Combine both the invoice and top-up lists, sort them by date, and print the transaction history
        user.printTransactionHistory();
    }

    @Then("the transaction history will display the following:")
    public void theTransactionHistoryWillDisplayTheFollowing(DataTable expectedTable) {
        // Validate the transaction history against the expected data in the Gherkin table

        List<List<String>> expectedData = expectedTable.asLists(String.class);

        List<TopUpItem> topUps = user.getTopUpList();
        List<InvoiceItem> invoices = user.getInvoiceItemList();

        // Validate the first top-up
        assertEquals(Double.parseDouble(expectedData.get(1).get(0)), topUps.get(0).getAmount());

        // Compare date string format for the top-up
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(expectedData.get(1).get(1), topUps.get(0).getDate().format(formatter));

        // Validate the first invoice
        assertEquals(expectedData.get(2).get(2), invoices.get(0).getInvoiceID());

        // Compare date string format for the invoice
        assertEquals(expectedData.get(2).get(1), invoices.get(0).getDate().format(formatter));
    }
}
