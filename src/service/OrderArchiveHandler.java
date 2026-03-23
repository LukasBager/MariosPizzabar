package service;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class OrderArchiveHandler {

    private static ArrayList<Customer> customers;
    private static ArrayList<Order> orders;

    public OrderArchiveHandler() {
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void addOrder(Customer customer, Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {

            writer.write(customer.getName() + ",");
            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getCustomerType() + ",");

            writer.write(order.getOrderNumber() + ",");
            writer.write(order.getSubTotal() + ",");
            writer.write(order.getDiscountPercentage() + ",");
            writer.write(order.getPaymentMethod().name() + ",");
            writer.write(order.getPizzaOrdered().getName() + ",");
            writer.write(order.getPizzaOrdered().getIngredients() + ",");
            writer.write(Double.toString(order.getPizzaOrdered().getPrice()));

            writer.write(System.lineSeparator());

            writer.flush();

        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public void readOrders() {
        customers.clear();
        orders.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");


                String customerName = parts[0];
                int customerPhoneNumber = Integer.parseInt(parts[1]);
                String customerType = parts[2];

                Customer customer;
                if (customerType.equals("VIP Customer")) {
                    customer = new VIPCustomer(customerName, customerPhoneNumber);
                } else if (customerType.equals("Employee Customer")) {
                    customer = new EmployeeCustomer(customerName, customerPhoneNumber);
                } else {
                    customer = new NormalCustomer(customerName, customerPhoneNumber);
                }

                customers.add(customer);


                int orderNumber = Integer.parseInt(parts[3]);
                double subTotal = Double.parseDouble(parts[4]);
                double discountPercentage = Double.parseDouble(parts[5]);
                PaymentMethod paymentMethod = PaymentMethod.valueOf(parts[6]);
                Pizza pizzaOrdered = new Pizza(parts[7], parts[8], Double.parseDouble(parts[9]));

                Order order = new Order(orderNumber, subTotal, discountPercentage, paymentMethod, pizzaOrdered);
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
