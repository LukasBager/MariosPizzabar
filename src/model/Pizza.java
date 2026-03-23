package model;

public class Pizza {
    private String name;
    private String ingredients;
    private double price;

    public Pizza(String name, String ingredients, double price){
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {return name;}
    public String getIngredients() {return ingredients;}
    public double getPrice() {return price;}

}
