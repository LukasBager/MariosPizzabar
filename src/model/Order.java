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
    private Pizza pizzaOrdered;


    // Constructor
    public Order(int orderNumber, double subTotal, double discountPercentage, PaymentMethod paymentMethod, Pizza pizzaOrdered) {
        this.orderNumber = orderNumber;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discount = subTotal * discountPercentage;
        this.total = subTotal - this.discount;
        this.paymentMethod = paymentMethod;
        this.pizzaOrdered = pizzaOrdered;
    }


    // Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Pizza getPizzaOrdered() {
        return pizzaOrdered;
    }


    // Setters
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPizzaOrdered(Pizza pizzaOrdered) {
        this.pizzaOrdered = pizzaOrdered;
    }


    // Manual toString metode
    @Override
    public String toString() {
        return "Order Number: " + orderNumber + " | Subtotal: $" + subTotal + " | Discount: $" + discount + " (" + discountPercentage + "%) | Total: $" + total + "\nPayment method: " + paymentMethod + "\nOrder: " + pizzaOrdered.getName();
    }

}
