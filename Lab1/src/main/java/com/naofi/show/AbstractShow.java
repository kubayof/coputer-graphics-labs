package com.naofi.show;

import javax.swing.*;
import java.awt.image.BufferedImage;

import com.naofi.commons.Painter;

public abstract class AbstractShow extends JLabel {
    public static class AbstractBuilder<T extends AbstractBuilder<?>> {
        protected final AbstractShow show;

        public AbstractBuilder(AbstractShow show) {
            this.show = show;
        }

        @SuppressWarnings("unchecked")
        public T size(int size) {
            show.size = size;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T point1(int x, int y) {
            show.x1 = x;
            show.y1 = y;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T point2(int x, int y) {
            show.x2 = x;
            show.y2 = y;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T pseudoPixelSize(int N) {
            if (N < 1) {
                throw new IllegalArgumentException("N must be at least 1");
            }
            show.N = N;
            return (T) this;
        }

        public AbstractShow build() {
            show.draw();
            return show;
        }
    }

    protected int size = 300;
    protected int x1 = 20, y1 = 10;
    protected int x2 = 50, y2 = 30;
    protected int N = 3;
    private final BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
    protected final Painter painter = new Painter(image.getGraphics(), N);

    private void draw() {
        painter.fillWhite(size, size);
        generateImage();
        Icon icon = new ImageIcon(image);
        setIcon(icon);
    }

    protected abstract void generateImage();
}
