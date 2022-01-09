package Werkzeug;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class Maus {

    private static Consumer<Position> onClick;

    public static void wennKlick(Consumer<Position> aktion) {
        onClick = aktion;
    }

    public static Position position() {
        try {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();

            Component[] components = Zeichner.frame.getComponents();

            Component canvas = components[0];

            return getPosition(b.x - canvas.getLocationOnScreen().x, b.y - canvas.getLocationOnScreen().y);

        } catch (Exception e) {
            return getPosition(0, 0);
        }
    }

    static class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            onClick.accept(buildPosition(e));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private static Position buildPosition(MouseEvent e) {
        return getPosition(e.getX(), e.getY());
    }

    private static Position getPosition(int pixelX, int pixelY) {
        Position position = new Position(pixelX / Zeichner.PIXEL_SIZE, pixelY / Zeichner.PIXEL_SIZE);
        if (position.x >= Zeichner.PIXEL_BREITE) {
            position.x = Zeichner.PIXEL_BREITE - 1;
        }

        if (position.x < 0) {
            position.x = 0;
        }

        if (position.y >= Zeichner.PIXEL_BREITE) {
            position.y = Zeichner.PIXEL_BREITE - 1;
        }

        if (position.y < 0) {
            position.y = 0;
        }

        return position;
    }
}
