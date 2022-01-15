package Aufgabe;

import Werkzeug.Grafik.Farbe;
import Werkzeug.Grafik.Zeichner;

//Aufgabe 5: Zeichne den kompletten Bildschirm rot
public class Aufgabe5 {
    public static void main(String[] args) {
        for (int y = 0; y < 100; y++) {
            skizziereReihe(y);
        }

        Zeichner.zeichnen();
    }

    private static void skizziereReihe(int y){
        for (int x = 0; x < 100; x++) {
            Zeichner.pixelSkizzieren(x,y,Farbe.ROT);
        }
    }
}

