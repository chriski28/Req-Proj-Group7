package org.example;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

    // Static variable to keep track of the next ID
    private static int nextId = 10000000; // Starts from an 8-digit number

    // Fields representing the attributes in the class diagram
    private String userId;
    private String name;

    private String username;
    private PaymentData paymentData; // Nested PaymentData class
    private double balance;
    private List<TopUpItem> topUpList; // List to store top-up transactions
    private List<InvoiceItem> invoiceItemList; // List to store invoice items

    // Constructor
    public User() {
        this.userId = generateUserId(); // Automatically generate a user ID
        this.balance = 0.0; // Default starting balance
        this.topUpList = new ArrayList<>(); // Initialize the top-up list
        this.invoiceItemList = new ArrayList<>(); // Initialize the invoice item list
    }
    public User(String name, String username, String iban, String cardholderName) {
        this.name = name;
        this.username = username;
        this.paymentData = new PaymentData(iban, cardholderName); // Initialize PaymentData with provided details
        this.userId = generateUserId(); // Automatically generate a user ID
        this.balance = 0.0; // Default starting balance
        this.topUpList = new ArrayList<>(); // Initialize the top-up list
        this.invoiceItemList = new ArrayList<>(); // Initialize the invoice item list
    }



    // Method to generate a unique 8-digit user ID
    private String generateUserId() {
        String newId = String.format("%08d", nextId); // Ensures the ID is always 8 digits
        nextId++; // Increment for the next user
        return newId;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TopUpItem> getTopUpList() {
        return topUpList;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    // Method to add a top-up to the user's balance and topUpList
    public void addTopUp(TopUpItem topUp) {
        this.balance += topUp.getAmount(); // Update balance
        this.topUpList.add(topUp); // Add to the top-up list
    }

    // Method to add an invoice item to the user's invoice list
    public void addInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItemList.add(invoiceItem);
    }

    // Method to print all invoices for the user
    public void printInvoice() {
        if (invoiceItemList.isEmpty()) {
            System.out.println("No invoices available for user " + this.name);
            return;
        }

        System.out.println("Invoices for user: " + this.name);
        for (InvoiceItem invoice : invoiceItemList) {
            System.out.println(invoice);
        }
    }

    // Method to start a charging session
    public String charge(Charger charger, int durationMinutes, LocalDateTime date) {
        InvoiceItem currentInvoiceItem = new InvoiceItem(this, charger, date, durationMinutes);

        // Calculate the total cost
        double totalCost = currentInvoiceItem.calculateInvoiceItemPrice();

        if (this.balance >= totalCost) {
            this.balance -= totalCost;
            charger.setStatus(Charger.Status.OPERATIONAL_BUSY);
            invoiceItemList.add(currentInvoiceItem);
            return "Charging session started successfully.";
        } else {
            return "Insufficient balance to start charging";
        }
    }

    // Method to check if the user has sufficient balance for a transaction
    public boolean hasSufficientBalance(double amount) {
        return this.balance >= amount;
    }

    // Nested PaymentData class
    public class PaymentData {
        public PaymentData(String iban, String cardholderName) {
            this.iban = iban;
            this.cardholderName = cardholderName;
        }
        public PaymentData(){};

        private String iban;
        private String cardholderName;

        // Getters and Setters
        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getCardholderName() {
            return cardholderName;
        }

        public void setCardholderName(String cardholderName) {
            this.cardholderName = cardholderName;
        }
    }

    // Method to print user details
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}