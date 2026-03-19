package model;

public abstract class Customer {

    // Attributes
    private String name;
    private int phoneNumber;
    private String customerType;
    private double discountPercentage;


    // Constructors
    public Customer() {
        this.name = "Unknown";
        this.phoneNumber = 0;
    }

    public Customer(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }


    // Manuel toString metode
    @Override
    public String toString() {
        return "Customer: " + name + " | Phone Number: " + phoneNumber + " | Discount percentage: " + discountPercentage + "%";
    }

}
