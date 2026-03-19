package model;

public class Pizza {
    private String name;
    private String ingredients;
    private int price;

    public Pizza(String name, String ingredients, int price){
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {return name;}
    public String getIngredients() {return ingredients;}
    public int getPrice() {return price;}

}
