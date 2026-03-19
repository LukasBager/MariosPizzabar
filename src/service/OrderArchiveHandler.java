package service;

import model.Customer;
import model.Order;
import model.Pizza;

import java.io.*;

public class OrderArchiveHandler {

    File orders = new File("orders.txt");

    public static void saveOrder(Customer customer, Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {

            writer.write(customer.getName() + ",");
            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getCustomerType().name() + ",");
            writer.write(customer.getDiscountPercentage() + ",");

            // ORDER NUMBER
            writer.write(order.getSubTotal() + ",");
            writer.write(order.getDiscountPercentage() + ",");
            writer.write(order.getDiscount() + ",");
            writer.write(order.getTotal() + ",");
            writer.write(order.getPaymentMethod().name() + ",");
            int lastIndex = order.getFoodOrdered().size() - 1;
            for (int i = 0; i < lastIndex; i++) {
                writer.write(order.getFoodOrdered().get(i).name() + ",");
            }
            writer.write(order.getFoodOrdered().get(lastIndex).name());

            writer.write(System.lineSeparator());

            writer.flush();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

}
