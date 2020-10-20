package com.naofi.commons;

import java.awt.*;
import java.util.function.BiConsumer;

public class Painter {
    private final Graphics graphics;
    private final int N;

    public Painter(Graphics graphics, int N) {
        this.graphics = graphics;
        this.N = N;
    }

    public void ddaLine(int x1, int y1, int x2, int y2) {
        double dx, dy, steps, x, y;
        double xc, yc;
        dx = x2 - x1;
        dy = y2 - y1;
        steps = Math.max(Math.abs(dx), Math.abs(dy));

        xc = (dx / steps);
        yc = (dy / steps);
        x = x1;
        y = y1;
        point(x, y);
        for (double k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            point(x, y);
        }
    }

    public void bresenhamLine(int x1, int y1, int x2, int y2)
    {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int stepX = Integer.compare(x2, x1);
        int stepY = Integer.compare(y2, y1);

        int error = dx-dy;
        int doubleError;

        while (true)
        {
            point(x1, y1);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            doubleError = 2 * error;
            if (doubleError > -dy)
            {
                error = error - dy;
                x1 = x1 + stepX;
            }

            if (doubleError < dx)
            {
                error = error + dx;
                y1 = y1 + stepY;
            }
        }
    }

    public void bresenhamCircle(int x0, int y0, int radius) {
        //New point function for coordinate system with origin in (x0, y0)
        BiConsumer<Integer, Integer> point = (a, b) -> {
            point(a + x0, b + y0);
        };

        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error;
        while (y >= 0) {
            point.accept(x, y);
            point.accept(x, -y);
            point.accept(-x, y);
            point.accept(-x, -y);

            error = 2 * (delta + y) - 1;
            if ((delta < 0) && (error <= 0)) {
                delta += 2 * ++x + 1;
                continue;
            }
            if ((delta > 0) && (error > 0)) {
                delta -= 2 * --y + 1;
                continue;
            }
            delta +=2 * (++x - --y);
        }
    }

    public void wuLine(int x1, int y1, int x2, int y2) {
        TriConsumer<Integer, Integer, Double> pointFunction = this::point;
        if(Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
            TriConsumer<Integer, Integer, Double> oldPointFunction = pointFunction;
            pointFunction = (x, y, b) -> oldPointFunction.accept(y, x, b);
        }

        if (x2 < x1) {
            int temp = x2;
            x2 = x1;
            x1 = temp;
            temp = y2;
            y2 = y1;
            y1 = temp;
        }

        float dx = x2 - x1;
        float dy = y2 - y1;
        float gradient;
        if (dx == 0) {
            gradient = 1;
        } else {
            gradient = dy/dx;
        }

        int xpxl1 = x1;
        int xpxl2 = x2;
        float intersectY = y1;

        for (int x = xpxl1; x <= xpxl2; x++) {
            double fpart = fpart(intersectY);
            pointFunction.accept(x, (int)intersectY, 1.0 - fpart);
            pointFunction.accept(x, (int)intersectY - 1, fpart);
            intersectY += gradient;
        }

    }

    private static float fpart(float val) {
        return val - (int)val;
    }

    public void point(double x, double y) {
        point((int) x, (int) y);
    }

    public void point(int x, int y) {
        graphics.setColor(Color.BLACK);
        pointHelper(x, y);
    }

    private void pointHelper(int x, int y) {
        int actualX = x * N;
        int actualY = y * N;
        int halfN = N / 2;
        graphics.fillRect(actualX - halfN, actualY - halfN, N + 1, N + 1);
    }

    public void point(int x, int y, double brightness) {
        int b256 = (int) (255 * brightness);
        if (b256 > 255) {
            System.err.println("Over: " + b256);
            b256 = 255;
        }
        Color color = new Color(b256, b256, b256);
        graphics.setColor(color);
        pointHelper(x, y);
    }

    public void fillWhite(int width, int height) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }
}
