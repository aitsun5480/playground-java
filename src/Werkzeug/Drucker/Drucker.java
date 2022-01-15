package Werkzeug.Drucker;

public class Drucker {
    public static void drucken(String text) {
        System.out.print(text);
    }

    public static void zahlDrucken(int zahl) {
        System.out.print(String.valueOf(zahl));
    }


    public static void neueZeileDrucken() {
        System.out.println();
    }
}
