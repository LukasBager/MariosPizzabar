package util;

public class ErrorHandler {
    public static void handle(Exception e){
        System.out.println("Der opstået en fejl: " + e.getMessage());
    }
    public static void handle(String message) {
        System.out.println("Fejl: " + message);}
}

