package service;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class OrderArchiveHandler {

    // Attributes
    private static ArrayList<Customer> customers;
    private static ArrayList<Order> orders;


    // Constructor
    public OrderArchiveHandler() {
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }


    // Methods
    public void addOrder(Customer customer, Order order) {
        readOrders();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {

            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getCustomerType() + ",");

            if (orders.isEmpty()) {
                writer.write(1 + ",");
            } else {
                int newOrderNumber = getLastOrderNumber() + 1;
                writer.write(newOrderNumber);
            }
            writer.write(order.getSubTotal() + ",");
            writer.write(order.getDiscountPercentage() + ",");
            writer.write(order.getPaymentMethod().name() + ",");
            int lastIndex = order.getFoodOrdered().size();
            for (int index = 0; index < lastIndex; index++) {
                Pizza currentPizza = order.getFoodOrdered().get(index);
                writer.write(currentPizza.getName() + ";" + currentPizza.getIngredients() + ";" + currentPizza.getPrice() + ",");
            }
            writer.write(order.getFoodOrdered().get(lastIndex).getName());

            writer.write(customer.getName() + ",");
            writer.write(System.lineSeparator());

            writer.flush();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }


    private void readOrders() {
        customers.clear();
        orders.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int phoneNumber = Integer.parseInt(parts[1]);
                String customerType = parts[2];
                // Customer customer = new Customer(name, phoneNumber, customerType);
                Customer customer;
                if (customerType.equals("Normal Customer")) {
                    customer = new NormalCustomer(name, phoneNumber);
                } else if (customerType.equals("VIP Customer")) {
                    customer = new VIPCustomer(name, phoneNumber);
                } else if (customerType.equals("Employee Customer")) {
                    customer = new EmployeeCustomer(name, phoneNumber);
                } else {
                    customer = new NormalCustomer(name, phoneNumber);
                    System.out.println("Invalid customer type in CSV file. Defaulting to Normal Customer.");
                }
                customers.add(customer);

                int orderNumber = Integer.parseInt(parts[3]);
                double subTotal = Double.parseDouble(parts[4]);
                double orderDiscountPercentage = Double.parseDouble(parts[5]);
                PaymentMethod paymentMethod = PaymentMethod.valueOf(parts[6]);
                ArrayList<Pizza> foodOrdered = new ArrayList<>();
                for (int i = 7; i <= parts.length; i++) {
                    String[] partsOfParts = parts[i].split(";");

                    String pizzaName = partsOfParts[0];
                    String pizzaIngredients = partsOfParts[1];
                    int pizzaPrice = Integer.parseInt(partsOfParts[2]);

                    Pizza pizza = new Pizza(pizzaName, pizzaIngredients, pizzaPrice);
                    foodOrdered.add(pizza);
                }
                Order order = new Order(orderNumber, subTotal, orderDiscountPercentage, paymentMethod, foodOrdered);
                orders.add(order);


            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }


    public int getLastOrderNumber() {
        readOrders();
        return orders.getLast().getOrderNumber();
    }

    public ArrayList<Order> getOrders() {
        readOrders();
        return orders;
    }

    public ArrayList<Customer> getCustomers() {
        readOrders();
        return customers;
    }

}
