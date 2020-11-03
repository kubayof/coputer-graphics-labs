package com.naofi.view.labels;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class PolygonLabel extends DrawableLabel {
    private final List<Point> points;

    public PolygonLabel(Point... points) {
        this.points = Arrays.asList(points);
    }

    @Override
    public BufferedImage getRawImage() {
        final int size = 300;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.BLACK);
        for (int i = 0; i < points.size() - 1; i++) {
            g.drawLine(points.get(i).x, points.get(i).y,
                    points.get(i + 1).x, points.get(i + 1).y);
        }

        g.drawLine(points.get(0).x, points.get(0).y,
                points.get(points.size() - 1).x, points.get(points.size() - 1).y);

        return image;
    }
}
