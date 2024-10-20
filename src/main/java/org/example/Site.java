package org.example;
/*first edit*/

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//there was no connection between site and charger? Added a charger list in site and a site

public class Site {

    //aloha

    // Attributes representing the details of the charging site
    private String address;
    private String name;
    private PriceTracker energyPriceAC = new PriceTracker();
    private PriceTracker energyPriceDC = new PriceTracker();
    private PriceTracker durationPriceAC = new PriceTracker();
    private PriceTracker durationPriceDC = new PriceTracker();
    private static LinkedList<Charger> chargerList = new LinkedList<Charger>();

    // PriceTracker nested class,
    // tree map is like a dictionary but with automatic sorting, makes finding price by date easier
    public class PriceTracker {
        // TreeMap to store price changes, automatically sorted by date
        private TreeMap<LocalDateTime, Double> priceHistory = new TreeMap<>();

        // Method to add a price change
        public void addPriceChange(LocalDateTime date, double price) {
            priceHistory.put(date, price);
        }

        // Method to find the price at a specific date
        public double getPriceOnDate(LocalDateTime date) {
            // Use floorEntry to find the closest date before or equal to the given date
            Map.Entry<LocalDateTime, Double> entry = priceHistory.floorEntry(date);
            if (entry != null) {
                return entry.getValue();
            } else {
                throw new IllegalArgumentException("No price data available before this date");
            }
        }
    }

    // Constructor
    public Site(){};
    public Site(String name){
        this.name=name;
    };
    public Site(String name, String address) {
        this.name = name;
        this.address = address;
        // Initialize the prices with default values (can be set later)
        // is this still necessary? Should we add starting prices + date or only set them during tests?
        /*
        this.energyPriceAC = 0.0;
        this.energyPriceDC = 0.0;
        this.durationPriceAC = 0.0;
        this.durationPriceDC = 0.0;
        */
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for energy prices, changed to fit the new system
    public double getEnergyPriceAC(LocalDateTime date) {
        return energyPriceAC.getPriceOnDate(date);
    }

    public void setEnergyPriceAC(LocalDateTime date, double price) {
        this.energyPriceAC.addPriceChange(date, price);
    }

    public double getEnergyPriceDC(LocalDateTime date) {
        return energyPriceDC.getPriceOnDate(date);
    }

    public void setEnergyPriceDC(LocalDateTime date, double price) {
        this.energyPriceDC.addPriceChange(date, price);
    }

    // Getter and Setter for duration prices, changed to fit the new system
    public double getDurationPriceAC(LocalDateTime date) {
        return durationPriceAC.getPriceOnDate(date);
    }

    public void setDurationPriceAC(LocalDateTime date, double price) {
        this.durationPriceAC.addPriceChange(date, price);
    }

    public double getDurationPriceDC(LocalDateTime date) {
        return durationPriceDC.getPriceOnDate(date);
    }

    public void setDurationPriceDC(LocalDateTime date, double price) {
        this.durationPriceDC.addPriceChange(date, price);
    }

    // toString() method to print site details, maybe different method is required for the report?
    // Argument in is a LocalDateTime and the method returns the same string as below but with only the prices relevant
    // for that date.
    @Override
    public String toString() {
        return "Site{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", energyPriceAC=" + energyPriceAC +
                ", energyPriceDC=" + energyPriceDC +
                ", durationPriceAC=" + durationPriceAC +
                ", durationPriceDC=" + durationPriceDC +
                '}';
    }
}

