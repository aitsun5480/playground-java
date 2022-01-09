package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Maus;
import Werkzeug.Position;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;
import Werkzeug.Zeit;

import java.awt.*;

public class Knyacki {
    private static Position position = new Position(Zeichner.PIXEL_BREITE / 2, Zeichner.PIXEL_BREITE / 2);
    private static Richtung richtung = Richtung.OBEN;

    private static final Color KOPF_FARBE = Farbe.GOLD;
    private static final Color KÖRPER_FARBE = Farbe.GRÜN;
    private static final Color WAND_FARBE = Farbe.ROT;

    private enum Richtung {
        RECHTS, LINKS, OBEN, UNTEN
    }

    public static void main(String[] args) {
        Tastatur.wennTaste(Taste.LINKS, Knyacki::linksDrehen);
        Tastatur.wennTaste(Taste.RECHTS, Knyacki::rechtsDrehen);

        bewegen();

        while (true) {
            if (Zeichner.pixelLesen(Maus.position().x, Maus.position().y) == null) {
                Zeichner.pixelSkizzieren(Maus.position().x, Maus.position().y, Farbe.ORANGE);
            } else {
                if (Zeichner.pixelLesen(Maus.position().x, Maus.position().y) == Farbe.ORANGE) {
                    Zeichner.pixelSkizzieren(Maus.position().x, Maus.position().y, Farbe.ROT);
                }
            }


            bewegen();
            Zeichner.zeichnen();
            Zeit.warten(100);
        }
    }

    public static void linksDrehen() {
        switch (richtung) {
            case OBEN:
                richtung = Richtung.LINKS;
                break;
            case RECHTS:
                richtung = Richtung.OBEN;
                break;
            case UNTEN:
                richtung = Richtung.RECHTS;
                break;
            case LINKS:
                richtung = Richtung.UNTEN;
                break;
        }
    }

    public static void rechtsDrehen() {
        switch (richtung) {
            case OBEN:
                richtung = Richtung.RECHTS;
                break;
            case RECHTS:
                richtung = Richtung.UNTEN;
                break;
            case UNTEN:
                richtung = Richtung.LINKS;
                break;
            case LINKS:
                richtung = Richtung.OBEN;
                break;
        }
    }

    public static void bewegen() {
        int neuePosX = position.x;
        int neuePosY = position.y;

        switch (richtung) {
            case OBEN:
                neuePosY--;
                break;
            case RECHTS:
                neuePosX++;
                break;
            case UNTEN:
                neuePosY++;
                break;
            case LINKS:
                neuePosX--;
                break;
        }

        if ((Zeichner.pixelLesen(neuePosX, neuePosY) == null || Zeichner.pixelLesen(neuePosX, neuePosY) == Farbe.ORANGE)  && neuePosX > 0 && neuePosX < Zeichner.PIXEL_BREITE&& neuePosY > 0 && neuePosY < Zeichner.PIXEL_BREITE) {
            Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);
            position.x = neuePosX;
            position.y = neuePosY;
            Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);
        }
    }
}

