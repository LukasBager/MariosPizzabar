package model;

import java.time.LocalDateTime;
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
    private LocalDateTime orderPlacedTime;
    private LocalDateTime orderPickupTime;


    // Constructor
    public Order(int orderNumber, double subTotal, double discountPercentage, PaymentMethod paymentMethod, Pizza pizzaOrdered) {
        this.orderNumber = orderNumber;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discount = subTotal * (discountPercentage / 100);
        this.total = subTotal - this.discount;
        this.paymentMethod = paymentMethod;
        this.pizzaOrdered = pizzaOrdered;
        this.orderPlacedTime = LocalDateTime.now();
        this.orderPickupTime = orderPlacedTime.plusMinutes(20);
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

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public LocalDateTime getOrderPickupTime() { return orderPickupTime; }


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

    public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public void setOrderPickupTime(LocalDateTime orderPickupTime) { this.orderPickupTime = orderPickupTime; }


    // Manual toString metode
    @Override
    public String toString() {
        return "Ordre nummer: " + orderNumber + " | Subtotal: $" + subTotal + " | Rabat: $" + discount + " (" + discountPercentage + "%) | Total: $" + total + "\nBetalingsmetode: " + paymentMethod + "\nOrdre: " + pizzaOrdered.getName();
    }

}
