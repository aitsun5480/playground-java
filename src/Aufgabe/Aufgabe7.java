package Aufgabe;

import Werkzeug.Grafik.Farbe;
import Werkzeug.Grafik.Zeichner;

import java.awt.*;

//Aufgabe 7: Zeichne die deutsche Flagge
public class Aufgabe7 {

    public static void main(String[] args) {
        skizziereBalken(0, 30,Farbe.SCHWARZ);
        skizziereBalken(30,60,Farbe.ROT);
        skizziereBalken(60,90,Farbe.GOLD);

        Zeichner.zeichnen();
    }

    public static void skizziereBalken(int minY, int maxY, Color Farbe) {
        for (int y = minY; y < maxY; y++) {
            skizziereReihe(y, Farbe);
        }
    }

    private static void skizziereReihe(int y, Color farbe) {
        for (int x = 0; x < 90; x++) {
            Zeichner.pixelSkizzieren(x, y, farbe);
        }
    }
}
