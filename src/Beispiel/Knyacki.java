package Beispiel;

import Werkzeug.Farbe;
import Werkzeug.Lautsprecher;
import Werkzeug.Position;
import Werkzeug.Tastatur;
import Werkzeug.Taste;
import Werkzeug.Zeichner;
import Werkzeug.Zeit;
import Werkzeug.Zufall;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Knyacki {
    private static Position position;
    private static Richtung richtung;
    private static Queue<Position> schwanz;
    private static int soundZähler;
    private static int endeZähler;
    private static int letzerEndeZählScore;
    private static int score;

    private static final Color KÖRPER_FARBE = Farbe.GRÜN;
    private static final Color WAND_FARBE = Farbe.ROT;
    private static final Color BAUEN_FARBE = Farbe.ORANGE;

    private static boolean istGameOver = true;

    private static final Feld[][] felder = new Feld[Zeichner.PIXEL_BREITE][Zeichner.PIXEL_BREITE];

    private enum Feld {
        KOPF, KÖRPER, BAUEN, WAND
    }

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
                score++;

                int außenWandDicke = (score / 50) + 1;
                for (int spaltenZähler = 0; spaltenZähler < Zeichner.PIXEL_BREITE; spaltenZähler++) {
                    for (int reihenZähler = 0; reihenZähler < Zeichner.PIXEL_BREITE; reihenZähler++) {
                        if (spaltenZähler < außenWandDicke || reihenZähler < außenWandDicke || spaltenZähler > Zeichner.PIXEL_BREITE - außenWandDicke - 1 || reihenZähler > Zeichner.PIXEL_BREITE - außenWandDicke - 1) {
                            Zeichner.pixelSkizzieren(spaltenZähler, reihenZähler, WAND_FARBE);
                            felder[spaltenZähler][reihenZähler] = Feld.WAND;
                        }
                    }
                }
            }

            Zeichner.textSkizzieren(String.valueOf(score));
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
        if (istGameOver) {
            Lautsprecher.abspielen("NewGame");
            for (int spaltenZähler = 0; spaltenZähler < Zeichner.PIXEL_BREITE; spaltenZähler++) {
                for (int reihenZähler = 0; reihenZähler < Zeichner.PIXEL_BREITE; reihenZähler++) {
                    if (spaltenZähler == 0 || reihenZähler == 0 || spaltenZähler == Zeichner.PIXEL_BREITE - 1 || reihenZähler == Zeichner.PIXEL_BREITE - 1) {
                        Zeichner.pixelSkizzieren(spaltenZähler, reihenZähler, WAND_FARBE);
                        felder[spaltenZähler][reihenZähler] = Feld.WAND;
                    } else {
                        Zeichner.pixelSkizzieren(spaltenZähler, reihenZähler, null);
                        felder[spaltenZähler][reihenZähler] = null;
                    }
                }
            }

            schwanz = new ArrayDeque<>();
            soundZähler = 0;
            endeZähler = 0;
            letzerEndeZählScore = 0;
            richtung = Richtung.OBEN;
            position = new Position(Zeichner.PIXEL_BREITE / 2, Zeichner.PIXEL_BREITE / 2);
            score = 0;
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
                felder[neuePosX][neuePosY] == Feld.KÖRPER || felder[neuePosX][neuePosY] == Feld.WAND) {
            if (score - letzerEndeZählScore > 10) {
                endeZähler++;
                switch (endeZähler) {
                    case 1:
                        Lautsprecher.abspielen("Ichi", 6);
                        break;
                    case 2:
                        Lautsprecher.abspielen("Ni", 6);
                        break;
                    case 3:
                        Lautsprecher.abspielen("San", 6);
                        break;
                    case 4:
                        Lautsprecher.abspielen("GameOver", 6);
                        istGameOver = true;

                }
                letzerEndeZählScore = score;
            }

            return;
        } else {
            letzerEndeZählScore = 0;
            endeZähler = 0;
        }

        if (felder[neuePosX][ neuePosY] == Feld.BAUEN) {
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
                    felder[schwanzSpitze.x][schwanzSpitze.y] = null;
                }
            }
        }

        if ((felder[neuePosX][neuePosY] == null || felder[neuePosX][neuePosY] == Feld.BAUEN)) {
            Zeichner.pixelSkizzieren(position.x, position.y, KÖRPER_FARBE);
            felder[position.x][position.y] = Feld.KÖRPER;
            position.x = neuePosX;
            position.y = neuePosY;
            Zeichner.bildSkizzieren(position.x, position.y, "Kopf");
            felder[position.x][position.y] = Feld.KOPF;
        }
    }

    private static void bauen() {
        int x = Zufall.zahl(0, Zeichner.PIXEL_BREITE - 1);
        int y = Zufall.zahl(0, Zeichner.PIXEL_BREITE - 1);
        if (felder[x][y] == null) {
            Zeichner.bildSkizzieren(x, y, "Kopf");
            felder[x][y] = Feld.BAUEN;
        } else {
            if (felder[x][y] == Feld.BAUEN) {
                Zeichner.pixelSkizzieren(x, y, WAND_FARBE);
                felder[x][y] = Feld.WAND;
                Lautsprecher.abspielen("Bauen");
            }
        }
    }
}

