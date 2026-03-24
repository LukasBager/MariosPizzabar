package service;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderArchiveHandler {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    private static ArrayList<Customer> customersSorted = new ArrayList<>();
    private static ArrayList<Order> ordersSorted = new ArrayList<>();

    public static void addOrder(Customer customer, Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.csv", true))) {

            writer.write(customer.getName() + ",");
            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getCustomerType() + ",");

            writer.write(order.getOrderNumber() + ",");
            writer.write(order.getSubTotal() + ",");
            writer.write(order.getDiscountPercentage() + ",");
            writer.write(order.getPaymentMethod().name() + ",");

            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String orderPlacedTimeFormatted = myFormatObj.format(order.getOrderPlacedTime());
            String orderPickupTimeFormatted = myFormatObj.format(order.getOrderPickupTime());
            writer.write(orderPlacedTimeFormatted + ",");
            writer.write(orderPickupTimeFormatted + ",");

            writer.write(order.getPizzaOrdered().getName() + ",");
            writer.write(order.getPizzaOrdered().getIngredients() + ",");
            writer.write(Double.toString(order.getPizzaOrdered().getPrice()));

            writer.write(System.lineSeparator());

            writer.flush();

        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public static void readOrders() {
        customers.clear();
        orders.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.csv"))){
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

                String orderPlacedTimeFormatted = parts[7];
                String orderPickupTimeFormatted = parts[8];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime orderPlacedTime = LocalDateTime.parse(orderPlacedTimeFormatted, formatter);
                LocalDateTime orderPickupTime = LocalDateTime.parse(orderPickupTimeFormatted, formatter);

                Pizza pizzaOrdered = new Pizza(parts[9], parts[10], Double.parseDouble(parts[11]));

                Order order = new Order(orderNumber, subTotal, discountPercentage, paymentMethod, orderPlacedTime, orderPickupTime, pizzaOrdered);
                orders.add(order);

            }
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public static int getLastOrderNumber() {
        readOrders();
        return orders.getLast().getOrderNumber();
    }

    public static ArrayList<Order> getOrders() {
        readOrders();
        return orders;
    }

    public static ArrayList<Customer> getCustomers() {
        readOrders();
        return customers;
    }

}
