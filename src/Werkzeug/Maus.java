package Werkzeug;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class Maus {

    private static Consumer<MausEvent> onClick;

    public static void wennKlick(Consumer<MausEvent> aktion) {
        onClick = aktion;
    }

    static class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            onClick.accept(buildMausEvent(e));
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

        private MausEvent buildMausEvent(MouseEvent e) {
            MausEvent mausEvent = new MausEvent();
            mausEvent.posX = e.getX() / Zeichner.PIXEL_SIZE;
            mausEvent.posY = e.getY() / Zeichner.PIXEL_SIZE;

            return mausEvent;
        }
    }
}
