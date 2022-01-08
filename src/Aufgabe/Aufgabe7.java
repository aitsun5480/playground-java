package Aufgabe;

import Werkzeug.Farbe;
import Werkzeug.Zeichner;

//Aufgabe 7: Zeichne die deutsche Flagge
public class Aufgabe7 {

    public static void main(String[] args) {
        skizziereSchwarzenBalken();
        skizziereGoldenenBalken();
        skizziereRoteBalken();

        Zeichner.zeichnen();
    }

    public static void skizziereSchwarzenBalken() {
        for (int y = 0; y < 30; y++) {
            skizziereSchwarzeReihe(y);
        }
    }

    private static void skizziereSchwarzeReihe(int y) {
        for (int x = 0; x < 90; x++) {
            Zeichner.pixelSkizzieren(x, y, Farbe.SCHWARZ);
        }
    }
    public static void skizziereRoteBalken() {
        for (int y = 30; y < 60; y++) {
            skizziereRoteReihe(y);
        }
    }

    private static void skizziereRoteReihe(int y) {
        for (int x = 0; x < 90; x++) {
            Zeichner.pixelSkizzieren(x, y, Farbe.ROT);
        }
    }


    public static void skizziereGoldenenBalken() {
        for (int y = 60; y < 90; y++) {
            skizziereGoldeneReihe(y);
        }
    }

    private static void skizziereGoldeneReihe(int y) {
        for (int x = 0; x < 90; x++) {
            Zeichner.pixelSkizzieren(x, y, Farbe.GOLD);
        }
    }
}

