package com.naofi.show;

import java.util.function.BiConsumer;

public class Algorithms {
    private Algorithms() {}

    public static void ddaLine(BiConsumer<Double, Double> pointFunction, int x1, int y1, int x2, int y2) {
        double dx, dy, steps, x, y;
        double xc, yc;
        dx = x2 - x1;
        dy = y2 - y1;
        steps = Math.max(Math.abs(dx), Math.abs(dy));

        xc = (dx / steps);
        yc = (dy / steps);
        x = x1;
        y = y1;
        pointFunction.accept(x, y);
        for (double k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            pointFunction.accept(x, y);
        }
    }

    public static void bresenhamLine(BiConsumer<Integer, Integer> pointFunction, int x1, int y1, int x2, int y2)
    {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int stepX = Integer.compare(x2, x1);
        int stepY = Integer.compare(y2, y1);

        int error = dx-dy;
        int doubleError;

        while (true)
        {
            pointFunction.accept(x1, y1);

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

    public static void bresenhamCircle(BiConsumer<Integer, Integer> pointFunction, int x0, int y0, int radius) {
        //New point function for coordinate system with origin in (x0, y0)
        BiConsumer<Integer, Integer> point = (a, b) -> {
            pointFunction.accept(a + x0, b + y0);
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

    public static void wuLine(TriConsumer<Integer, Integer, Double> pointFunction, int x1, int x2, int y1, int y2) {
        if (x2 < x1) {
            int temp = x2;
            x2 = x1;
            x1 = temp;
            temp = y2;
            y2 = y1;
            y1 = temp;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;
        double gradient = dy/(double)dx;

        //Start point
        long xend = Math.round(gradient);
        double yend = y1 + gradient* (xend - x1);
        double xgap = 1 - fpart(x1 + 0.5);
        double xpxl1 = xend;
        double ypxl1 = ipart(yend);
        pointFunction.accept((int)xpxl1, (int)ypxl1, (1 - fpart(yend)) * xgap);
        pointFunction.accept((int)xpxl1, (int)ypxl1 + 1, fpart(yend) * xgap);
        double intery = yend + gradient;

        //End point
        xend = round(x2);
        yend = y2 + gradient * (xend - x2);
        xgap = fpart(x2 + 0.5);
        double xpxl2 = xend;
        double ypxl2 = ipart(yend);
        pointFunction.accept((int)xpxl2, (int)ypxl2, (1 - fpart(yend)) * xgap);
        pointFunction.accept((int)xpxl2, (int)ypxl2 + 1, fpart(yend) * xgap);

        for (double x = xpxl1 + 1; x <= xpxl2 - 1; x++) {
            pointFunction.accept((int)x, ipart(intery), 1 - fpart(intery));
            pointFunction.accept((int)x, ipart(intery) + 1, fpart(intery));
            intery = intery + gradient;
        }
    }

    private static int ipart(double x) {
        return (int)x;
    }

    private static int round(double x) {
        return (int)Math.round(x);
    }

    private static double fpart(double x) {
        return x - (int)x;
    }
}
