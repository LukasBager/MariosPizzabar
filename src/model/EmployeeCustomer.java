package model;

public class EmployeeCustomer extends Customer {

    public EmployeeCustomer() {
        super();
        this.setCustomerType("Employee Customer");
        this.setDiscountPercentage(0.2);
    }

    public EmployeeCustomer(String name, int phoneNumber) {
        super(name, phoneNumber);
        this.setCustomerType("Employee Customer");
        this.setDiscountPercentage(0.2);
    }

}
