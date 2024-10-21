package org.example;

import java.time.LocalDateTime;

public class TopUpItem {
    private final String type = "TopUp";

    private double amount;
    private LocalDateTime date;

    // Constructor that accepts both amount and date
    public TopUpItem(double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date; // Set the date to the provided LocalDateTime
    }

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TopUpItem{" +
                "type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}