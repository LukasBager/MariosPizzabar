package model;

import java.util.ArrayList;

public class Order {

    // Attributes
    private int orderNumber;
    private double subTotal;
    private double discountPercentage;
    private double discount;
    private double total;
    private PaymentMethod paymentMethod;
    private ArrayList<String> foodOrdered;


    // Constructor
    public Order(double subTotal, PaymentMethod paymentMethod, double discountPercentage, ArrayList<String> foodOrdered) {
        // ADD LOGIC TIL ORDER NUMBER
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discount = subTotal * discountPercentage;
        this.total = subTotal - this.discount;
        this.paymentMethod = paymentMethod;
        this.foodOrdered = foodOrdered;
    }


    // Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public ArrayList<String> getFoodOrdered() {
        return foodOrdered;
    }


    // Manual toString metode
    @Override
    public String toString() {
        return "Order Number: " + orderNumber + " | Subtotal: $" + subTotal + " | Discount: $" + discount + " | Total: $" + total + "\nPayment method: " + paymentMethod + "\nOrder:\n" + foodOrdered;
    }

}
