package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    // Attributes
    private int orderNumber;
    private double subTotal;
    private double discountPercentage;
    private double discount;
    private double total;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderPlacedTime;
    private LocalDateTime orderPickupTime;
    private String orderPlacedTimeString;
    private String orderPickupTimeString;
    private Pizza pizzaOrdered;


    // Constructor
    public Order(int orderNumber, double subTotal, double discountPercentage, PaymentMethod paymentMethod, LocalDateTime orderPlacedTime, LocalDateTime orderPickupTime, Pizza pizzaOrdered) {
        this.orderNumber = orderNumber;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discount = Math.round((subTotal * (discountPercentage / 100)) * 100.0) / 100.0;
        this.total = Math.round((subTotal - this.discount) * 100.0) / 100.0;
        this.paymentMethod = paymentMethod;
        this.orderPlacedTime = orderPlacedTime;
        this.orderPickupTime = orderPickupTime;
        this.orderPlacedTimeString = orderPlacedTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.orderPickupTimeString = orderPickupTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
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

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public LocalDateTime getOrderPickupTime() { return orderPickupTime; }

    public String getOrderPlacedTimeString() {
        return orderPlacedTimeString;
    }

    public String getOrderPickupTimeString() {
        return orderPickupTimeString;
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

    public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public void setOrderPickupTime(LocalDateTime orderPickupTime) { this.orderPickupTime = orderPickupTime; }

    public void setOrderPlacedTimeString(String orderPlacedTimeString) {
        this.orderPlacedTimeString = orderPlacedTimeString;
    }

    public void setOrderPickupTimeString(String orderPickupTimeString) {
        this.orderPickupTimeString = orderPickupTimeString;
    }

    public void setPizzaOrdered(Pizza pizzaOrdered) {
        this.pizzaOrdered = pizzaOrdered;
    }


    // Manual toString metode
    @Override
    public String toString() {
        return "Ordre nummer: " + orderNumber + " | Subtotal: $" + subTotal + " | Rabat: $" + discount + " (" + discountPercentage + "%) | Total: $" + total + "\nBetalingsmetode: " + paymentMethod + "\nOrdre: " + pizzaOrdered.getName() + "\nBestillingstid: " + orderPlacedTimeString + "\nAfhentningstid: " + orderPickupTimeString;
    }

}
