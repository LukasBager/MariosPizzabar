package model;

public class Customer {

    // Attributes
    private String name;
    private int phoneNumber;
    private CustomerType customerType;
    private double discountPercentage;


    // Constructors
    public Customer() {
        this.name = "Unknown";
        this.phoneNumber = 0;
        this.customerType = CustomerType.NormalCustomer;
        this.discountPercentage = 0;
    }

    public Customer(String name, int phoneNumber, CustomerType customerType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        if (customerType.equals(CustomerType.NormalCustomer)) {
            this.discountPercentage = 0;
        } else if (customerType.equals(CustomerType.VIPCustomer)) {
            this.discountPercentage = 10;
        } else if (customerType.equals(CustomerType.EmployeeCustomer)) {
            this.discountPercentage = 20;
        } else {
            this.discountPercentage = 0;
            System.out.println("An error has occured with customer type.");
        }
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerType getCustomerType() {
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

    public void setCustomerType(CustomerType customerType) {
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
