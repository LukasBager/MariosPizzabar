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

    public void saveOrders() {
        readOrders();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {

            for (int i = 0; i < customers.size() && i < orders.size(); i++) {

                Customer currentCustomer = customers.get(i);
                Order currentOrder = orders.get(i);

                writer.write(currentCustomer.getName() + ",");
                writer.write(currentCustomer.getPhoneNumber() + ",");
                writer.write(currentCustomer.getCustomerType().name() + ",");

                if (orders.isEmpty()) {
                    writer.write(1 + ",");
                } else {
                    int newOrderNumber = getLastOrderNumber() + 1;
                    writer.write(newOrderNumber);
                }
                writer.write(currentOrder.getSubTotal() + ",");
                writer.write(currentOrder.getDiscountPercentage() + ",");
                writer.write(currentOrder.getPaymentMethod().name() + ",");
                int lastIndex = currentOrder.getFoodOrdered().size() - 1;
                for (int index = 0; i < lastIndex; index++) {
                    Pizza currentPizza = currentOrder.getFoodOrdered().get(index);
                    writer.write(currentPizza.getName() + ";" + currentPizza.getIngredients() + ";" + currentPizza.getPrice() + ",");
                }
                writer.write(currentOrder.getFoodOrdered().get(lastIndex).getName());

                writer.write(System.lineSeparator());
            }

            writer.flush();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

    public void readOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int phoneNumber = Integer.parseInt(parts[1]);
                CustomerType customerType = CustomerType.valueOf(parts[2]);
                Customer customer = new Customer(name, phoneNumber, customerType);
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
        int lastOrderNumber = orders.getLast().getOrderNumber();
        return lastOrderNumber;
    }

}
