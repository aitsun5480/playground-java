package Aufgabe;

import Werkzeug.Drucker.Drucker;

// Aufgabe:  Drucke die Zahlen 1 bis 10, jeweils auf einer eigenen Zeile.
//タスク：1から10までの数字をそれぞれ別々の行に印刷します。
public class Aufgabe1 {
    public static void main(String[] args) {
        Drucker.neueZeileDrucken();
        Drucker.drucken("1");
        Drucker.neueZeileDrucken();
        Drucker.drucken("2");
        Drucker.neueZeileDrucken();
        Drucker.drucken("3");
        Drucker.neueZeileDrucken();
        Drucker.drucken("4");
        Drucker.neueZeileDrucken();
        Drucker.drucken("5");
        Drucker.neueZeileDrucken();
        Drucker.drucken("6");
        Drucker.neueZeileDrucken();
        Drucker.drucken("7");
        Drucker.neueZeileDrucken();
        Drucker.drucken("8");
        Drucker.neueZeileDrucken();
        Drucker.drucken("9");
        Drucker.neueZeileDrucken();
        Drucker.drucken("10");
    }
}
