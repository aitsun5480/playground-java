package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Maus;
import Werkzeug.Position;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;
import Werkzeug.Zeit;

import java.awt.*;

//Aufgabe 7: Zeichne die japanische Flagge
public class Knyacki {
    private static Position position = new Position(50, 50);
    private static boolean fliegtGerade = false;
    private static final Color KOPF_FARBE = Farbe.GOLD;
    private static final Color KÖRPER_FARBE = Farbe.GRÜN;

    public static void main(String[] args) {
        Tastatur.wennTaste(Taste.LINKS, Knyacki::links);
        Tastatur.wennTaste(Taste.RECHTS, Knyacki::rechts);
        Tastatur.wennTaste(Taste.OBEN, Knyacki::oben);
        Tastatur.wennTaste(Taste.UNTEN, Knyacki::unten);
        Tastatur.wennTaste(Taste.SPACE, Knyacki::fliegen);
        Maus.wennKlick(Knyacki::klick);

        Zeichner.zeichnen();

        while (true) {
            if (fliegtGerade) {
                position = Maus.position();
                Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);
                Zeichner.zeichnen();
            }
            Zeit.warten(13);
        }
    }

    private static void fliegen() {
        fliegtGerade = !fliegtGerade;
    }

    public static void klick(Position event) {
        position = event;

        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void links() {
        Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);

        position.x--;
        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void rechts() {
        Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);

        position.x++;
        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void oben() {
        Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);

        position.y--;
        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void unten() {
        Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);

        position.y++;
        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }
}

