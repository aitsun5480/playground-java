package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Maus;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;

import java.awt.*;

//Aufgabe 7: Zeichne die japanische Flagge
public class Knyacki {
    private static int posX = 50;
    private static int posY = 50;

    private static final Color KOPF_FARBE = Farbe.GOLD;
    private static final Color KÖRPER_FARBE = Farbe.GRÜN;

    public static void main(String[] args) {

        Tastatur.wennTaste(Taste.LINKS, Knyacki::links);
        Tastatur.wennTaste(Taste.RECHTS, Knyacki::rechts);
        Tastatur.wennTaste(Taste.OBEN, Knyacki::oben);
        Tastatur.wennTaste(Taste.UNTEN, Knyacki::unten);

        Maus.wennKlick(event -> {
            posX = event.posX;
            posY = event.posY;

            Zeichner.pixelSkizzieren(posX, posY, KOPF_FARBE);

            Zeichner.zeichnen();
        });

        Zeichner.zeichnen();
    }

    public static void links() {
        Zeichner.pixelSkizzieren(posX, posY, KÖRPER_FARBE);

        posX--;
        Zeichner.pixelSkizzieren(posX, posY, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void rechts() {
        Zeichner.pixelSkizzieren(posX, posY, KÖRPER_FARBE);

        posX++;
        Zeichner.pixelSkizzieren(posX, posY, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void oben() {
        Zeichner.pixelSkizzieren(posX, posY, KÖRPER_FARBE);

        posY--;
        Zeichner.pixelSkizzieren(posX, posY, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void unten() {
        Zeichner.pixelSkizzieren(posX, posY, KÖRPER_FARBE);

        posY++;
        Zeichner.pixelSkizzieren(posX, posY, KOPF_FARBE);

        Zeichner.zeichnen();
    }
}

