package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import service.OrderArchiveHandler;

public class MariosPizzabarUI {

    static Scanner scanner;
    static Menu menu = new Menu();
    static OrderArchiveHandler orderArchiveHandler = new OrderArchiveHandler();

    public MariosPizzabarUI() {
        scanner = new Scanner(System.in);
    }


    public void start() {
        System.out.println("Velkommen til Marios Pizzabar.");
        boolean running;
        do {
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
                    running = true;
                    break;
                case 2:
                    addOrder();
                    running = true;
                    break;
                case 3:
                    printOrders();
                    running = true;
                    break;
                case 4:
                    printCustomers();
                    running = true;
                    break;
                case 5:
                    printSortedOrders();
                    running = true;
                    break;
                case 6:
                    printSortedCustomers();
                    running = true;
                    break;
                default:
                    running = false;
                    break;
            }

        } while (running);
    }

    public static void printOptions() {
        System.out.println();
        System.out.println("Vælg en mulighed forneden:");
        System.out.println("1. Print menu");
        System.out.println("2. Opret ordre");
        System.out.println("3. Se ordrehistorik");
        System.out.println("4. Se liste over kunder");
        System.out.println("5. Se ordreliste sorteret efter beløb");
        System.out.println("6. Se kundeliste sorteret alfabetisk efter navn");
        System.out.println("7. Afslut");
    }

    public static void printMenu() {
        menu.printMenu();
    }

    public static void addOrder() {

        System.out.println("Indtast kundens navn");
        String customerName = scanner.nextLine();

        System.out.println("Indtast kundens telefonnummer");
        int customerPhoneNumber = 12345678;

        boolean phoneNumVerified = false;
        while (!phoneNumVerified) {
            if (scanner.hasNextInt()) {
                customerPhoneNumber = scanner.nextInt();
                phoneNumVerified = true;
            } else {
                System.out.println("Ugyldigt input. Prøv igen");
            }
            scanner.nextLine();
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

        boolean pizzaChoiceVerified = false;
        int pizzaChosen = 0;
        while (!pizzaChoiceVerified) {
            if (scanner.hasNextInt()) {
                pizzaChosen = scanner.nextInt();
                if (pizzaChosen > menu.getPizzas().size() || pizzaChosen < 1) {
                    System.out.println("Pizza nummer ugyldigt, prøv igen.");
                } else {
                    pizzaChoiceVerified = true;
                }
            } else {
                System.out.println("Ugyldigt input, prøv igen.");
            }
            scanner.nextLine();
        }


        int pizzaIndex = pizzaChosen - 1;
        Pizza pizza = menu.choosePizza(pizzaIndex);


        System.out.println("Vælg en betalingsmetode:");
        System.out.println("1. Kontant");
        System.out.println("2. Debit kort");
        System.out.println("3. Kredit kort");
        System.out.println("4. Gavekort");
        System.out.println("5. Apple Pay");

        boolean paymentMethodChoiceVerified = false;
        int paymentMethodChoice = 0;
        while (!paymentMethodChoiceVerified) {
            if (scanner.hasNextInt()) {
                paymentMethodChoice = scanner.nextInt();
                if (paymentMethodChoice > PaymentMethod.values().length | paymentMethodChoice < 1) {
                    System.out.println("Ugyldigt input, prøv igen.");
                } else {
                    paymentMethodChoiceVerified = true;
                }
            } else {
                System.out.println("Ugyldigt input, prøv igen.");
            }
            scanner.nextLine();
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

        LocalDateTime orderPlacedTime = LocalDateTime.now();
        LocalDateTime orderPickupTime = orderPlacedTime.plusMinutes(20);

        Order order = new Order(orderNumber, subTotal, discountPercentage, paymentMethod, orderPlacedTime, orderPickupTime, pizza);

        orderArchiveHandler.addOrder(customer, order);
        System.out.println("Ordre tilføjet d. " + order.getOrderPlacedTime());

    }

    public static void printOrders() {
        ArrayList<Order> orders = orderArchiveHandler.getOrders();
        ArrayList<Customer> customers = orderArchiveHandler.getCustomers();
        System.out.println();
        if (orders.isEmpty()) {
            System.out.println("Ordrelisten er tom.");
        } else {
            System.out.println("Ordre:");
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
            System.out.println("Kundelisten er tom.");
        } else {
            System.out.println("Kunder:");
            for (Customer c : customers) {
                System.out.println(c);
            }
        }
    }

    public static void printSortedOrders() {
        ArrayList<Order> ordersSorted = orderArchiveHandler.getSortedOrders();
        System.out.println();
        if (ordersSorted.isEmpty()) {
            System.out.println("Ordreliste er tom.");
        } else {
            System.out.println("Ordre sorteret efter beløb:");
            for (Order o : ordersSorted) {
                System.out.println(o);
                System.out.println();
            }
        }
    }

    public static void printSortedCustomers() {
        ArrayList<Customer> customersSorted = orderArchiveHandler.getSortedCustomers();
        System.out.println();
        if (customersSorted.isEmpty()) {
            System.out.println("Kundelisten er tom.");
        } else {
            System.out.println("Kunder sorteret alfabetisk efter navn:");
            for (Customer c : customersSorted) {
                System.out.println(c);
            }
        }
    }

}
