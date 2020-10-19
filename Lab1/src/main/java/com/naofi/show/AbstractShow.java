package com.naofi.show;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractShow extends JLabel {
    public static class AbstractBuilder<T extends AbstractBuilder<?>> {
        protected final AbstractShow show;

        public AbstractBuilder(AbstractShow show) {
            this.show = show;
        }

        @SuppressWarnings("unchecked")
        public T size(int size) {
            show.size = size;
            return (T)this;
        }

        @SuppressWarnings("unchecked")
        public T point1(int x, int y) {
            show.x1 = x;
            show.y1 = y;
            return (T)this;
        }

        @SuppressWarnings("unchecked")
        public T point2(int x, int y) {
            show.x2 = x;
            show.y2 = y;
            return (T)this;
        }

        @SuppressWarnings("unchecked")
        public T pseudoPixelSize(int N) {
            if (N < 1) {
                throw new IllegalArgumentException("N must be at least 1");
            }
            show.N = N;
            return (T)this;
        }

        public AbstractShow build() {
            show.draw();
            return show;
        }
    }

    protected int size = 300;
    protected int x1 = 20, y1 = 230;
    protected int x2 = 20, y2 = 30;
    protected int N = 1;
    private final BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

    private void draw() {
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        generateImage();
        Icon icon = new ImageIcon(image);
        setIcon(icon);
    }

    protected void point(double x, double y) {
        point((int)x, (int)y);
    }

    protected void point(int x, int y) {
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        pointHelper(x, y);
    }

    protected void point(int x, int y, double brightness) {
        int b256 = (int)(256 * brightness);
        Color color = new Color(b256, b256, b256);
        image.getGraphics().setColor(color);
        pointHelper(x, y);
    }

    private void pointHelper(int x, int y) {
        int actualX = x * N;
        int actualY = y * N;
        int halfN = N / 2;
        image.getGraphics()
                .fillOval(actualX - halfN, actualY - halfN, N + 1, N + 1);
    }

    protected abstract void generateImage();
}
