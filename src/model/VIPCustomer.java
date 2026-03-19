package model;

public class VIPCustomer extends Customer {

    public VIPCustomer() {
        super();
        this.setCustomerType("VIP Customer");
        this.setDiscountPercentage(10);
    }

    public VIPCustomer(String name, int phoneNumber) {
        super(name, phoneNumber);
        this.setCustomerType("VIP Customer");
        this.setDiscountPercentage(10);
    }

}
