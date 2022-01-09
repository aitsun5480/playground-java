package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;
import Werkzeug.Zeit;

//Aufgabe 7: Zeichne die japanische Flagge
public class Steuerung {
    private static int posX = 50;
    private static int posY = 50;


    public static void main(String[] args) {

        Tastatur.wennTaste(Taste.LINKS, Steuerung::links);
        Tastatur.wennTaste(Taste.RECHTS, Steuerung::rechts);
        Tastatur.wennTaste(Taste.OBEN, Steuerung::oben);
        Tastatur.wennTaste(Taste.UNTEN, Steuerung::unten);

        while (true) {
            Zeichner.pixelSkizzieren(posX, posY, Farbe.GRÜN);
            Zeichner.zeichnen();

            Zeit.warten(13);
        }
    }

    public static void links() {
        Zeichner.pixelSkizzieren(posX, posY, Farbe.ROT);

        posX--;
        Zeichner.pixelSkizzieren(posX, posY, Farbe.GRÜN);

        Zeichner.zeichnen();
    }

    public static void rechts() {
        Zeichner.pixelSkizzieren(posX, posY, Farbe.ROT);

        posX++;
        Zeichner.pixelSkizzieren(posX, posY, Farbe.GRÜN);

        Zeichner.zeichnen();
    }

    public static void oben() {
        Zeichner.pixelSkizzieren(posX, posY, Farbe.ROT);

        posY--;
        Zeichner.pixelSkizzieren(posX, posY, Farbe.GRÜN);

        Zeichner.zeichnen();
    }

    public static void unten() {
        Zeichner.pixelSkizzieren(posX, posY, Farbe.ROT);

        posY++;
        Zeichner.pixelSkizzieren(posX, posY, Farbe.GRÜN);

        Zeichner.zeichnen();
    }
}

