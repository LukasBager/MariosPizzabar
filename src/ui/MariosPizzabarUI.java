package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import service.OrderArchiveHandler;

public class MariosPizzabarUI {

    static Scanner scanner;
    static Menu menu = new Menu();
    static OrderArchiveHandler orderArchiveHandler;

    public MariosPizzabarUI() {
        scanner = new Scanner(System.in);
        orderArchiveHandler = new OrderArchiveHandler();
    }


    public void start() {
        System.out.println("Velkommen til Marios Pizzabar.");
        boolean running = true;
        while (running) {
            printOptions();

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ugyldigt input");
            }

            switch (choice) {
                case 1:
                    printMenu();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    printOrders();
                    break;
                case 4:
                    printCustomers();
                    break;
                case 5:
                    running = false;
                    break;
            }

        }
    }

    public static void printOptions() {
        System.out.println();
        System.out.println("Vælg en mulighed forneden:");
        System.out.println("1. Print menu");
        System.out.println("2. Opret ordre");
        System.out.println("3. Se ordrehistorik");
        System.out.println("4. Se liste over kunder");
        System.out.println("5. Afslut");
    }

    public static void printMenu() {
        menu.printMenu();
    }

    public static void addOrder() {

        System.out.println("Indtast kundens navn");
        String customerName = scanner.nextLine();

        System.out.println("Indtast kundens telefonnummer");
        int customerPhoneNumber = 12345678;
        if (scanner.hasNextInt()) {
            customerPhoneNumber = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Ugyldigt input. Defaultet til telefonnummer 12345678");
        }

        System.out.println("Vælg kundetype:");
        System.out.println("1: Normal kunde");
        System.out.println("2: VIP Kunde");
        System.out.println("3: Medarbejder");

        int customerTypeChoice = 1;
        if (scanner.hasNextInt()) {
            customerTypeChoice = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Ugyldigt input. Defaulter til Normal kunde");
        }

        Customer customer;
        switch (customerTypeChoice) {
            case 2:
                customer = new VIPCustomer(customerName, customerPhoneNumber);
                break;
            case 3:
                customer = new EmployeeCustomer(customerName, customerPhoneNumber);
                break;
            default:
                customer = new NormalCustomer(customerName, customerPhoneNumber);
        }


        printMenu();
        System.out.println("Vælg en pizza foroven");

        int pizzaChosen;
        if (scanner.hasNextInt()) {
            pizzaChosen = scanner.nextInt();
        } else {
            pizzaChosen = 1;
            System.out.println("Ugyldigt input. Defaulter til en Vesuvio");
        }

        int pizzaIndex = pizzaChosen - 1;
        Pizza pizza = menu.choosePizza(pizzaIndex);


        System.out.println("Vælg en betalingsmetode:");
        System.out.println("1. Kontant");
        System.out.println("2. Debit kort");
        System.out.println("3. Kredit kort");
        System.out.println("4. Gavekort");
        System.out.println("5. Apple Pay");

        int paymentMethodChoice;
        if (scanner.hasNextInt()) {
            paymentMethodChoice = scanner.nextInt();
            scanner.nextLine();
        } else {
            paymentMethodChoice = 1;
            System.out.println("Ugyldigt input. Defaulter til kontant betaling.");
        }

        PaymentMethod paymentMethod;
        switch (paymentMethodChoice) {
            case 2:
                paymentMethod = PaymentMethod.DEBIT_CARD;
                break;
            case 3:
                paymentMethod = PaymentMethod.CREDIT_CARD;
                break;
            case 4:
                paymentMethod = PaymentMethod.GIFT_CARD;
                break;
            case 5:
                paymentMethod = PaymentMethod.APPLE_PAY;
                break;
            default:
                paymentMethod = PaymentMethod.CASH;
        }


        orderArchiveHandler.readOrders();
        int orderNumber;
        if (orderArchiveHandler.getOrders().isEmpty()) {
            orderNumber = 1;
        } else {
            orderNumber = orderArchiveHandler.getLastOrderNumber() + 1;
        }

        double subTotal = pizza.getPrice();
        double discountPercentage = customer.getDiscountPercentage();

        Order order = new Order(orderNumber, subTotal, discountPercentage, paymentMethod, pizza);

        orderArchiveHandler.addOrder(customer, order);
        System.out.println("Order added");

    }

    public static void printOrders() {
        ArrayList<Order> orders = orderArchiveHandler.getOrders();
        ArrayList<Customer> customers = orderArchiveHandler.getCustomers();
        System.out.println();
        if (orders.isEmpty()) {
            System.out.println("Order list empty.");
        } else {
            System.out.println("Orders:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println(customers.get(i));
                System.out.println(orders.get(i));
                System.out.println();
            }
        }
    }

    public static void printCustomers() {
        ArrayList<Customer> customers = orderArchiveHandler.getCustomers();
        System.out.println();
        if (customers.isEmpty()) {
            System.out.println("Customer list empty.");
        } else {
            System.out.println("Customers:");
            for (Customer c : customers) {
                System.out.println(c);
            }
        }
    }

}
