package Aufgabe;

import Werkzeug.Drucker.Drucker;

//Aufgabe 2: Drucke die Zahlen 1 - 1000
//演習2：1から1000までの数字を印刷します
public class Aufgabe2 {
    public static void main(String[] args) {
        for (int zähler = 1; zähler <= 1000; zähler++) {
            Drucker.neueZeileDrucken();
            Drucker.zahlDrucken(zähler);
        }
    }
}