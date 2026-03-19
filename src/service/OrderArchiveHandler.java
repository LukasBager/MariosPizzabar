package service;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class OrderArchiveHandler {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void saveOrder(Customer customer, Order order) {
        readOrders();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {

            writer.write(customer.getName() + ",");
            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getCustomerType().name() + ",");
            writer.write(customer.getDiscountPercentage() + ",");

            // ORDER NUMBER
            if (orders.isEmpty()) {
                writer.write(1 + ",");
            } else {
                int orderNumberCurrent = order.getOrderNumber() + 1;
                writer.write(orderNumberCurrent + ",");
            }
            writer.write(order.getSubTotal() + ",");
            writer.write(order.getDiscountPercentage() + ",");
            writer.write(order.getDiscount() + ",");
            writer.write(order.getTotal() + ",");
            writer.write(order.getPaymentMethod().name() + ",");
            int lastIndex = order.getFoodOrdered().size() - 1;
            for (int i = 0; i < lastIndex; i++) {
                writer.write(order.getFoodOrdered().get(i).getName() + ",");
            }
            writer.write(order.getFoodOrdered().get(lastIndex).getName());

            writer.write(System.lineSeparator());

            writer.flush();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public static void readOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int phoneNumber = Integer.parseInt(parts[1]);
                CustomerType customerType = CustomerType.valueOf(parts[2]);
                double discountPercentage = Double.parseDouble(parts[3]);
                Customer customer = new Customer(name, phoneNumber, customerType);
                customers.add(customer);

                int orderNumber = Integer.parseInt(parts[4]);
                double subTotal = Double.parseDouble(parts[5]);
                double orderDiscountPercentage = Double.parseDouble(parts[6]);
                double discount = Double.parseDouble(parts[7]);
                double total = Double.parseDouble(parts[8]);
                PaymentMethod paymentMethod = PaymentMethod.valueOf(parts[9]);
                ArrayList<Pizza> foodOrdered = new ArrayList<>();
                for (int i = 10; i <= parts.length; i++) {
                    Pizza pizza = new Pizza(parts[i]);
                }


            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

}
