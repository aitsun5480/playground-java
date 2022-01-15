package Aufgabe;

import Werkzeug.Grafik.Farbe;
import Werkzeug.Grafik.Zeichner;

//Aufgabe 4: Zeichne einen roten Pixel in der Mitte des Bildschirms
public class Aufgabe4 {
    public static void main(String[] args) {
        Zeichner.pixelSkizzieren(50,50, Farbe.ROT);
        Zeichner.zeichnen();
    }
}
