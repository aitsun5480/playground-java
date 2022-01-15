package Aufgabe;

import Werkzeug.Grafik.Farbe;
import Werkzeug.Grafik.Zeichner;

//Aufgabe 6: Zeichne die Bildschirmmitte rot
public class Aufgabe6 {
    public static void main(String[] args) {
        for (int y = 30; y < 70; y++) {
            skizziereReihe(y);
        }

        Zeichner.zeichnen();
    }

    private static void skizziereReihe(int y){
        for (int x = 30; x < 70; x++) {
            Zeichner.pixelSkizzieren(x,y,Farbe.ROT);
        }
    }
}

