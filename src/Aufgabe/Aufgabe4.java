package Aufgabe;

import Werkzeug.Farbe;
import Werkzeug.Zeichner;

//Aufgabe 4: Zeichne einen roten Pixel in der Mitte des Bildschirms
public class Aufgabe4 {
    public static void main(String[] args) {
        Zeichner.zeichnen();
        Zeichner.pixelSkizzieren(50,50, Farbe.ROT);
    }
}
