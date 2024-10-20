package org.example;

import java.time.LocalDate;

public class TopUpItem {

    private double amount;
    private LocalDate date;

    // Constructor
    public TopUpItem(double amount) {
        this.amount = amount;
        this.date = LocalDate.now(); // Set the date to today's date
    }

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
