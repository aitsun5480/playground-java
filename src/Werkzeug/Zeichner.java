package Werkzeug;

import javax.swing.*;
import java.awt.*;

public class Zeichner {
    public static final int PIXEL_BREITE = 100;
    static final int PIXEL_SIZE = 8;

    static Canvas canvas = null;
    static final JFrame frame = new JFrame(getMainClassName());

    private static final Color[][] pixels = new Color[PIXEL_BREITE][PIXEL_BREITE];

    private static class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < PIXEL_BREITE; i++) {
                Color[] line = pixels[i];
                for (int j = 0; j < PIXEL_BREITE; j++) {
                    Color pixel = line[j];
                    if (pixel != null) {
                        g.setColor(pixel);
                        g.fillRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                    }
                }
            }
        }
    }

    private static String getMainClassName() {
        StackTraceElement trace[] = Thread.currentThread().getStackTrace();
        if (trace.length > 0) {
            return trace[trace.length - 1].getClassName();
        }

        return "Zeichner";
    }

    public static void pixelSkizzieren(int x, int y, Color color) {
        pixels[x][y] = color;
    }

    public static Color pixelLesen(int x, int y) {
        return pixels[x][y];
    }

    public static void zeichnen() {
        SwingUtilities.invokeLater(Zeichner::draw);
    }

    private static void draw() {
        initialize();

        frame.getContentPane().repaint();
    }

    private static void initialize() {
        if (Zeichner.canvas == null) {
            Canvas canvas = new Canvas();
            frame.addKeyListener(new Tastatur.Listener());
            canvas.addMouseListener(new Maus.Listener());
            frame.setMinimumSize(new Dimension(PIXEL_SIZE * PIXEL_BREITE, PIXEL_SIZE * PIXEL_BREITE + 30));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(canvas);
            frame.setVisible(true);
            frame.pack();
            Zeichner.canvas = canvas;
        }
    }
}
