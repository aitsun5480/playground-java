package Aufgabe;

import Werkzeug.Drucker;

//Aufgabe 3: Drucke ein Mal Hallo, dann zwei Mal, dann drei Mal usw. Bis 1000 Mal hallo gedruckt wir
//  Bsp:
// Hallo
//
// Hallo
// Hallo
//
// Hallo
// Hallo
// Hallo
//
// Hallo
// Hallo
// Hallo
// Hallo
//演習2：1から1000までの数字を印刷します
public class Aufgabe3 {
    public static void main(String[] args) {
        int x = 1;
        while (x <= 10) {
            druckeXMalHallo(x);
            Drucker.neueZeileDrucken();
            x = x + 1;
        }
    }

    private static void druckeXMalHallo(int x) {
        int zähler = 1;
        while (zähler <= x) {
            Drucker.drucken("Hallo");
            Drucker.neueZeileDrucken();
            zähler = zähler + 1;
        }
    }
}