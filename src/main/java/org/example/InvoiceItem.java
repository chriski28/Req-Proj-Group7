package org.example;

import java.time.LocalDateTime;
import java.util.Date;

//made structural changes to the class, based on the lecturer's feedback
//added self generating invoiceItemID, removed start and end date, added duration, energy and duration price
//are obtained from Site.java via the new price tracking system
//moved attribute energyConsumed to the other attributes so we can use it in toString()

public class InvoiceItem {
    private static int nextID = 10000000;
    private String invoiceID;
    private LocalDateTime date;
    private int duration; //measured in minutes
    private double energyConsumed; //measured in kWh
    private User client;
    private Charger charger;
    private double energyPrice;
    private double durationPrice;



    // Constructor; I put everything into the constructor but depending on how the feature is structured we probably
    // want a leaner constructor and assign all the other values via Setters
    public InvoiceItem(User client, Charger charger, LocalDateTime date, int duration) {
        this.invoiceID = generateInvoiceItemID();
        this.date = date;
        this.client = client;
        this.charger = charger;
        this.duration = duration;
        this.energyPrice = charger.getType().equals(Charger.Type.AC) ? charger.getSite().getEnergyPriceAC(date) :
                charger.getSite().getEnergyPriceDC(date);
        this.durationPrice = charger.getType().equals(Charger.Type.AC) ? charger.getSite().getDurationPriceAC(date) :
                charger.getSite().getDurationPriceDC(date);
        this.energyConsumed = charger.getType().equals(Charger.Type.AC) ? (7.0 / 60) * duration : (50.0 / 60) * duration;
    }

    // Method to generate a unique 8-digit user ID
    private String generateInvoiceItemID(){
        String newId = String.format("%08d", nextID); // Ensures the ID is always 8 digits
        nextID++; // Increment for the next user
        return newId;
    }

    // Getters and Setters
    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public double getEnergyPrice() {
        return energyPrice;
    }

    public void setEnergyPrice(double energyPrice) {
        this.energyPrice = energyPrice;
    }

    public double getDurationPrice() {
        return durationPrice;
    }

    public void setDurationPrice(double durationPrice) {
        this.durationPrice = durationPrice;
    }


    // Method to calculate the total price of the invoice
    public double calculateInvoiceItemPrice() {
        return (durationPrice * duration) + (energyPrice * energyConsumed);
    }

    // Override toString to display invoice details
    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoiceID=" + invoiceID +
                ", date=" + date.toString() +
                ", client=" + client.getUserId() +
                ", duration=" + duration + "minutes" +
                ", energyConsumed=" + energyConsumed + "kWh" +
                ", chargerType=" + charger.getType() +
                ", totalPrice=" + calculateInvoiceItemPrice() +
                '}';
    }
}
