package com.naofi.lab4.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarnsleyFern {
    private static class Dp {
        public double x;
        public double y;

        public Dp(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    private final int COUNT_ITER = 1000000;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public BarnsleyFern() {
        paintImage(image.getGraphics());
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void paintImage(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.GREEN);

        Dp point = new Dp(0, 0);
        Dp screen;
        for (int i = 0; i < COUNT_ITER; i++) {
            int prob = (int)(Math.random() * 100);
            if (prob == 0) {
                point = transform1(point);
            } else if ((prob >= 1) && (85 >= prob)) {
                point = transform2(point);
            } else if ((prob >= 86) && (92 >= prob)) {
                point = transform3(point);
            } else {
                point = transform4(point);
            }
            screen = toScreenCoords(point);
            graphics.fillOval((int)screen.x, (int)screen.y, 2,2);
        }

    }

    private Dp toScreenCoords(Dp point) {
        double s = 100;
        return new Dp(point.x * s + WIDTH / 2.0,  -point.y * s + HEIGHT);
    }

    private Dp transform1(Dp in) {
        return new Dp(0, 0.16 * in.getY());
    }

    private Dp transform2(Dp in) {
        return new Dp(0.85*in.getX() + 0.04* in.getY(), -0.04*in.getX() + 0.85*in.getY() + 1.6);
    }

    private Dp transform3(Dp in) {
        return new Dp(0.2*in.getX() - 0.26*in.getY(), 0.23*in.getX() + 0.22*in.getY() + 1.6);
    }

    private Dp transform4(Dp in) {
        return new Dp(-0.15*in.getX() + 0.28*in.getY(), 0.26*in.getX() + 0.24*in.getY() + 0.44);
    }
}
