package org.example;

//Added attribute site so we can get the price list from the site linked to a charger

public class Charger {

    // Nested class for Status
    public static class Status {
        public static final Status OPERATIONAL_FREE = new Status("Operational Free");
        public static final Status OPERATIONAL_BUSY = new Status("Operational Busy");
        public static final Status OUT_OF_ORDER = new Status("Out of Order");

        private final String status;

        // Private constructor to prevent direct instantiation
        private Status(String status) {
            this.status = status;
        }

        // toString() method to return the status as a string
        @Override
        public String toString() {
            return status;
        }
    }

    // Nested class for Type
    public static class Type {
        public static final Type AC = new Type("AC");
        public static final Type DC = new Type("DC");

        private final String type;

        // Private constructor to prevent direct instantiation
        private Type(String type) {
            this.type = type;
        }

        // toString() method to return the type as a string
        @Override
        public String toString() {
            return type;
        }
    }

    // Attributes for the Charger class
    private Status status;
    private Type type;
    private Site site;

    // Constructor to initialize a charger with a specific type and default status
    public Charger(){};
    public Charger(Site site,Type type) {
        this.site = site;
        this.type = type;
        this.status = Status.OPERATIONAL_FREE;  // Set default status to "operational free"
    }
    // Getter for the site
    public Site getSite() {
        return site;
    }
    //Setter for the site
    public void setSite(Site site) {
        this.site = site;
    }

    // Getter for the status
    public Status getStatus() {
        return status;
    }

    // Setter for the status
    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter for the type
    public Type getType() {
        return type;
    }

    // Setter for the type
    public void setType(Type type) {
        this.type = type;
    }

    // toString() method to print charger details
    @Override
    public String toString() {
        return "Charger{" +
                "site=" + site +
                "status=" + status +
                ", type=" + type +
                '}';
    }
}
