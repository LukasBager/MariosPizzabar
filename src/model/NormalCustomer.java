package model;

public class NormalCustomer extends Customer {

    public NormalCustomer() {
        super();
        this.setCustomerType("Normal Customer");
        this.setDiscountPercentage(0);
    }

    public NormalCustomer(String name, int phoneNumber) {
        super(name, phoneNumber);
        this.setCustomerType("Normal Customer");
        this.setDiscountPercentage(0);
    }

}
