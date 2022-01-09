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
    private static Position position = new Position(50, 50);
    private static boolean löschtGerade = false;
    private static final Color KOPF_FARBE = Farbe.GOLD;
    private static final Color KÖRPER_FARBE = Farbe.GRÜN;

    public static void main(String[] args) {
        Tastatur.wennTaste(Taste.LINKS, Knyacki::linksBewegen);
        Tastatur.wennTaste(Taste.RECHTS, Knyacki::rechtsBewegen);
        Tastatur.wennTaste(Taste.OBEN, Knyacki::obenBewegen);
        Tastatur.wennTaste(Taste.UNTEN, Knyacki::untenBewegen);
        Tastatur.wennTaste(Taste.SPACE, Knyacki::löschenAktivieren);
        Maus.wennKlick(Knyacki::telportieren);

        Zeichner.zeichnen();

        while (true) {
            if (löschtGerade && Zeichner.pixelLesen(Maus.position().x,Maus.position().y) == KÖRPER_FARBE) {
                position = Maus.position();
                Zeichner.pixelSkizzieren(position.x, position.y, null);
                Zeichner.zeichnen();
            }
            Zeit.warten(13);
        }
    }

    private static void löschenAktivieren() {
        löschtGerade = !löschtGerade;
    }

    public static void telportieren(Position event) {
        Zeichner.pixelSkizzieren(position.x, position.y, null);

        position = event;

        Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);

        Zeichner.zeichnen();
    }

    public static void linksBewegen() {
        bewegen(position.x - 1, position.y);
    }

    public static void rechtsBewegen() {
        bewegen(position.x + 1, position.y);

    }

    public static void obenBewegen() {
        bewegen(position.x, position.y - 1);

    }

    public static void untenBewegen() {
        bewegen(position.x, position.y + 1);
    }

    public static void bewegen(int neuePosX, int neuePosY) {
        if (Zeichner.pixelLesen(neuePosX, neuePosY) == null) {
            Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);
            position.x = neuePosX;
            position.y = neuePosY;
            Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);
            Zeichner.zeichnen();
        }
    }
}

