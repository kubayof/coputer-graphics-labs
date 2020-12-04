package com.naofi.lab4.images;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Snowflake {
    private final List<Point> points;
    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

    public Snowflake(int depth) {
        this(new Point(250, 250), 200, depth);
    }

    public Snowflake(Point centerPoint, int size, int depth) {
        List<Point> startPoints = new ArrayList<>();
        startPoints.add(new Point(
                (int)(centerPoint.x - size * Math.cos(Math.PI / 6)),
                (int)(centerPoint.y + size * Math.sin(Math.PI / 6))
        ));
        startPoints.add(new Point(
                (int)(centerPoint.x + size * Math.cos(Math.PI / 6)),
                (int)(centerPoint.y + size * Math.sin(Math.PI / 6))
        ));
        startPoints.add(new Point(
                centerPoint.x,
                centerPoint.y - size
        ));
        points = calculatePoints(startPoints, depth);
        paintImage(image.getGraphics());
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void paintImage(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 500, 500);
        graphics.setColor(Color.BLACK);
        Point prev = points.get(points.size() - 1);
        for (Point cur : points) {
            graphics.drawLine(prev.x, prev.y, cur.x, cur.y);
            prev = cur;
        }
    }

    private List<Point> calculatePoints(List<Point> points, int depth) {
        if (depth == 0) {
            return points;
        }
        List<Point> newPoints = new ArrayList<>();

        Point prev = points.get(points.size() - 1);
        for (Point cur : points) {
            Point p1 = new Point(
                    cur.x + (prev.x - cur.x) / 3,
                    cur.y + (prev.y - cur.y) / 3
            );
            Point p2 = new Point(
                    cur.x + (prev.x - cur.x) / 3 * 2,
                    cur.y + (prev.y - cur.y) / 3 * 2
            );
            int cx = p1.x + (p2.x - p1.x) / 2;
            int cy = p1.y + (p2.y - p1.y) / 2;
            double h = Point.distance(p1.x, p1.y, p2.x, p2.y);
            double p12x = p2.x - p1.x;
            double p12y = p2.y - p1.y;
            double diag = Math.sqrt(p12x * p12x + p12y * p12y);
            double sin = p12y / diag;
            double cos = p12x / diag;
            double newX = cx + h * sin;
            double newY = cy - h * cos;
            Point p3 = new Point((int)newX, (int)newY);

            newPoints.add(p2);
            newPoints.add(p3);
            newPoints.add(p1);
            newPoints.add(cur);
            prev = cur;
        }

        return calculatePoints(newPoints, depth - 1);
    }
}
