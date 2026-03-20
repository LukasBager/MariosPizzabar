package model;
import util.ErrorHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;


public class Menu {

    private List<Pizza> pizzas = new ArrayList<>();
    //.'er
    public Menu() {
        pizzas.add(new Pizza("Vesuvio","tomatsauce, ost, skinke og oregano", 57));
        pizzas.add(new Pizza("Amerikaner", "tomatsauce, ost, oksefars og oregano", 53));
        pizzas.add(new Pizza("Cacciatore", "tomatsauce, ost, pepperoni og oregano", 57));
        pizzas.add(new Pizza("Carbonara", "tomatsauce, ost, kødsauce, spaghetti og cocktailpølser og oregano", 63));
        pizzas.add(new Pizza("Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano..", 65));
        pizzas.add(new Pizza("Bertil", "tomatsauce, ost, bacon og oregano..............................", 57));
        pizzas.add(new Pizza("Silva", "tomatsauce, ost pepperoni, rød peber, løg, oliven og oregano....", 61));
        pizzas.add(new Pizza("Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano..", 61));
        pizzas.add(new Pizza("Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano", 61));
        pizzas.add(new Pizza("Capricciosa", "tomatsauce, ost skinke, champignon og oregano", 61));
        pizzas.add(new Pizza("Hawai", "tomatsauce, ost, skinke, ananas og oregano", 61));
        pizzas.add(new Pizza("Le Blissola", "tomatsauce, ost, skinke, rejer og oregano", 61));
        pizzas.add(new Pizza("Venezia", "tomatsauce, ost, skinke, bacon og oregano", 61));
        pizzas.add(new Pizza("Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano", 61));
    }

    public void printMenu() {
        System.out.println("\n--- PIZZAER ---");
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p = pizzas.get(i);
            System.out.println((i + 1) + " " + p.getName() + " " + p.getIngredients() + " " + p.getPrice() + " kr");
        }
    }

    public Pizza choosePizza(Scanner scanner){
        System.out.print("Vælg en pizza ud fra nummeret: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice>=1 && choice <= pizzas.size()){

            } else {
                System.out.println("Ugyldig input");
            }

        } catch (NumberFormatException e){
            System.out.println("Ugyldigt input, skal være et tal");
        }
        return null;
    }




}



