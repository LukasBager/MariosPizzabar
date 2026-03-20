package ui;
import model.*;
import service.OrderArchiveHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class MariosPizzabarUI {

    private Menu menu = new Menu();
    private Scanner scanner = new Scanner(System.in);

    public void start(){
        boolean On = true;

        while (On){
            System.out.println("--- Marios Pizzabar ---");
            System.out.println("1. Se menu");
            System.out.println("2. Opret ordre");
            System.out.println("3. Afslut");

            System.out.print("Vælg: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    menu.printMenu();
                    break;

                case "2":
                    createOrder();
                    break;

                case "3":
                    On = false;
                    System.out.println("Lukker");
                    break;

                default:
                    System.out.println("Ugyldigt input");
            }



        }


    }

    private void createOrder(){
        System.out.println("Navn: ");
        String name = scanner.nextLine();

        System.out.println("Telefonnummer: ");
        int phoneNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Kundetype: 1 = Normal, 2 = VIP, 3 = medarbejder");
        String typeInput = scanner.nextLine();

        Customer customer;
        if (typeInput.equals("2")){
            customer = new VIPCustomer(name, phoneNumber);
        }
        else if (typeInput.equals("3")) {
            customer = new EmployeeCustomer(name, phoneNumber);
        } else {
            customer = new NormalCustomer(name, phoneNumber);
        }

        menu.printMenu();
        Pizza pizza = menu.choosePizza(scanner);

        if(pizza != null) {
            ArrayList<Pizza> foodOrdered = new ArrayList<>();
            foodOrdered.add(pizza);

            double subtotal = pizza.getPrice();
            double discountPercentage = customer.getDiscountPercentage();

            System.out.println("Vælg betalingsmetode 1 = Kontant, 2 = Debetkort, 3 = Kreditkort, 4 = Gavekort, 5 = Apple Pay");
            String paymentInput = scanner.nextLine();
            PaymentMethod paymentMethod = PaymentMethod.CASH;
            switch (paymentInput){
                case "2": paymentMethod = PaymentMethod.DEBIT_CARD;
                break;
                case "3": paymentMethod = PaymentMethod.CREDIT_CARD;
                break;
                case "4": paymentMethod = PaymentMethod.GIFT_CARD;
                break;
                case "5": paymentMethod = PaymentMethod.APPLE_PAY;

            }

            Order order = new Order(1, subtotal, discountPercentage,paymentMethod, foodOrdered);

            System.out.println("Ordre oprettet");
            System.out.println(customer);
            System.out.println(order);


        }

    }


}
