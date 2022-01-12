package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Lautsprecher;
import Werkzeug.Maus;
import Werkzeug.Position;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;
import Werkzeug.Zeit;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Knyacki {
    private static Position position;
    private static Richtung richtung;
    private static Queue<Position> schwanz;
    private static int soundZähler;

    private static final Color KOPF_FARBE = Farbe.GOLD;
    private static final Color KÖRPER_FARBE = Farbe.GRÜN;
    private static final Color WAND_FARBE = Farbe.ROT;
    private static final Color BAUEN_FARBE = Farbe.ORANGE;

    private static boolean istGameOver = true;

    private enum Richtung {
        RECHTS, LINKS, OBEN, UNTEN
    }

    public static void main(String[] args) {
        Tastatur.wennTaste(Taste.LINKS, Knyacki::linksDrehen);
        Tastatur.wennTaste(Taste.RECHTS, Knyacki::rechtsDrehen);
        Tastatur.wennTaste(Taste.SPACE, Knyacki::neuesSpiel);

        while (true) {
            if (!istGameOver) {
                bewegen();
                bauen();
            }

            Zeichner.zeichnen();
            Zeit.warten(100);
        }
    }

    private static void linksDrehen() {
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

    private static void rechtsDrehen() {
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

    private static void neuesSpiel() {
        Lautsprecher.abspielen("NewGame");
        if (istGameOver) {
            for (int spaltenZähler = 0; spaltenZähler < Zeichner.PIXEL_BREITE; spaltenZähler++) {
                for (int reihenZähler = 0; reihenZähler < Zeichner.PIXEL_BREITE; reihenZähler++) {
                    if (spaltenZähler == 0 || reihenZähler == 0 || spaltenZähler == Zeichner.PIXEL_BREITE - 1 || reihenZähler == Zeichner.PIXEL_BREITE - 1) {
                        Zeichner.pixelSkizzieren(spaltenZähler, reihenZähler, WAND_FARBE);
                    } else {
                        Zeichner.pixelSkizzieren(spaltenZähler, reihenZähler, null);
                    }
                }
            }

            schwanz = new ArrayDeque<>();
            soundZähler = 0;
            richtung = Richtung.OBEN;
            position = new Position(Zeichner.PIXEL_BREITE / 2, Zeichner.PIXEL_BREITE / 2);

            istGameOver = false;
        }
    }

    private static void bewegen() {
        schwanz.add(new Position(position.x, position.y));
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

        if ((neuePosX < 0 || neuePosX >= Zeichner.PIXEL_BREITE || neuePosY < 0 || neuePosY >= Zeichner.PIXEL_BREITE) ||
                (Zeichner.pixelLesen(neuePosX, neuePosY) == KÖRPER_FARBE || Zeichner.pixelLesen(neuePosX, neuePosY) == WAND_FARBE)) {
            Lautsprecher.abspielen("GameOver");
            istGameOver = true;
            return;
        }

        if (Zeichner.pixelLesen(neuePosX, neuePosY) == BAUEN_FARBE) {
            switch (soundZähler % 2) {
                case 0:
                    Lautsprecher.abspielen("KnyackiObachan");
                    break;
                case 1:
                    Lautsprecher.abspielen("KnyackiChan");
                    break;
            }

            soundZähler++;

            for (int zähler = 0; zähler < 10; zähler++) {
                if (schwanz.size() > 0) {

                    Position schwanzSpitze = schwanz.remove();
                    Zeichner.pixelSkizzieren(schwanzSpitze.x, schwanzSpitze.y, null);
                }
            }
        }

        if ((Zeichner.pixelLesen(neuePosX, neuePosY) == null || Zeichner.pixelLesen(neuePosX, neuePosY) == BAUEN_FARBE)) {
            Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);
            position.x = neuePosX;
            position.y = neuePosY;
            Zeichner.pixelSkizzieren(position.x, position.y, KOPF_FARBE);
        }
    }

    private static void bauen() {
        if (Zeichner.pixelLesen(Maus.position().x, Maus.position().y) == null) {
            Zeichner.pixelSkizzieren(Maus.position().x, Maus.position().y, BAUEN_FARBE);
        } else {
            if (Zeichner.pixelLesen(Maus.position().x, Maus.position().y) == BAUEN_FARBE) {
                Zeichner.pixelSkizzieren(Maus.position().x, Maus.position().y, WAND_FARBE);
                Lautsprecher.abspielen("Bauen");
            }
        }
    }
}

