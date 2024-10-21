package org.example;
/*
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

        InvoiceItem currentInvoiceItem = new InvoiceItem(clients.get(0), chargers.get(0), temp, duration);
        invoiceItems.add(currentInvoiceItem);
    }

    @Then("a new invoiceItem will be created with the following information")
    public void a_new_invoice_item_will_be_created_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> attributes = dataTable.asLists(String.class);

        String expectedOutput = "InvoiceItem{" +
                "invoiceID=" + attributes.get(1).get(0) +
                ", date=" + attributes.get(1).get(1) +
                ", client=" + attributes.get(1).get(2) +
                ", duration=" + attributes.get(1).get(3) + "minutes" +
                ", energyConsumed=" + attributes.get(1).get(4) + "kWh" +
                ", chargerType=" + attributes.get(1).get(5) +
                ", totalPrice=" + attributes.get(1).get(6) +
                '}';

        assertEquals(expectedOutput,invoiceItems.get(0).toString(), "blablab");
    }
}

 */