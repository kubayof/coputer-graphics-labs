package com.naofi.show;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BezierView extends JPanel {
    private final int pointSize = 50;

    private final int N;
    private final List<Point> points;

    public BezierView(int size, int N, Point... points) {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        this.N = N;
        this.points = new ArrayList(Arrays.asList(points));
        setSize(size, size);
        MouseAdapter adapter = new MouseAdapter() {
            private Point point;
            private final Point displacement = new Point();

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                point = findNearestPoint(x, y);
                if (point != null) {
                    if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                        BezierView.this.points.remove(point);
                        repaint();
                    }
                    displacement.setLocation(point.x - x, point.y - y);
                } else {
                    if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                        BezierView.this.points.add(new Point(x, y));
                        repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                point = null;
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if (point != null) {
                    point.setLocation(mouseEvent.getX() + displacement.getX(),
                            mouseEvent.getY() + displacement.getY());
                }
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    private ControlsPanel.Info transformations;
    public void transform(ControlsPanel.Info info) {
        Point center = getBezierCenterCoordinates();
        for (Point point : points) {
            Point transformed = coordinatesWithTransformations(point, center);
            point.setLocation(transformed);
        }
        repaint();
    }

    public void setTransformations(ControlsPanel.Info info) {
        transformations = info;
        repaint();
    }

    public void removeTransformations() {
        transformations = null;
        repaint();
    }

    private Point coordinatesWithTransformations(Point original, Point center) {
        if (transformations != null) {
            Point transformed = rotate(transformations.getRotate(), original, center);
            if (transformations.isMirrorByX()) {
                transformed = mirrorByX(transformed, center);
            }
            if (transformations.isMirrorByY()) {
                transformed = mirrorByY(transformed, center);
            }
            transformed = scale(transformations.getScale(), transformed, center);
            transformed = displace(transformations.getXDisplacement(), transformations.getYDisplacement(), transformed);
            return transformed;
        }
        return original;
    }

    private Point displace(int x, int y, Point original) {
        return new Point(original.x + x, original.y + y);
    }

    private Point scale(int factor, Point original, Point center) {
        int newX = (original.x - center.x) * factor + center.x;
        int newY = (original.y - center.y) * factor + center.y;

        return new Point(newX, newY);
    }

    private Point mirrorByY(Point original, Point center) {
        int newY = - (original.y - center.y) + center.y;

        return new Point(original.x, newY);
    }

    private Point mirrorByX(Point original, Point center) {
        int newX = - (original.x - center.x) + center.x;

        return new Point(newX, original.y);
    }

    private Point rotate(int angle, Point original, Point center) {
        int x = original.x - center.x;
        int y = original.y - center.y;

        double radianAngle = (double)angle / 180 * Math.PI;
        int newX = (int)(x * Math.cos(radianAngle) + y * Math.sin(radianAngle));
        int newY = (int)(y * Math.cos(radianAngle) - x * Math.sin(radianAngle));

        return new Point(newX + center.x, newY + center.y);
    }

    private Point getBezierCenterCoordinates() {
        long aggregateX = 0;
        long aggregateY = 0;
        for (Point point : points) {
            aggregateX += point.x;
            aggregateY += point.y;
        }
        return new Point((int)(aggregateX / points.size()), (int)(aggregateY / points.size()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.ORANGE);

        if (points.size() >= 1) {
            Graphics2D g2d = (Graphics2D) g.create();
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);

            Point center = getBezierCenterCoordinates();
            Point lastPoint = coordinatesWithTransformations(points.get(0), center);
            Point point;
            int halfPointSize = pointSize / 2;
            for (int i = 1; i < points.size(); i++) {
                point = coordinatesWithTransformations(points.get(i), center);
                g2d.setColor(Color.BLACK);
                g2d.drawLine(lastPoint.x + halfPointSize, lastPoint.y + halfPointSize,
                        point.x + halfPointSize, point.y + halfPointSize);
                lastPoint = point;
            }

            for (Point p : points) {
                Point transformed = coordinatesWithTransformations(p, center);
                g.fillOval(transformed.x, transformed.y, pointSize, pointSize);
            }

            drawBezierCurve(g2d);
            g2d.dispose();
        }
    }

    private Point findNearestPoint(int x, int y) {
        return points.stream()
                .filter(p -> {
                    double dx = p.getX() - x;
                    double dy = p.getY() - y;
                    return Math.sqrt(dx*dx + dy*dy) <= pointSize;
                })
                .findFirst().orElse(null);
    }

    private void drawBezierCurve(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        Point last = null;
        Point current = new Point();
        Point center = getBezierCenterCoordinates();
        for (double step = 0.01, t = 0; t < step + 1; t += step) {
            if (t > 1) {
                t = 1;
            }

            double b;
            double x = 0;
            double y = 0;
            int halfPointSize = pointSize / 2;
            for (int i = 0; i < points.size(); i++) {
                b = j(i, points.size() - 1, t);
                Point transformed = coordinatesWithTransformations(points.get(i), center);
                x += (transformed.x + halfPointSize) * b;
                y += (transformed.y + halfPointSize) * b;
            }

            current.setLocation(x, y);
            if (last != null) {
                g2d.drawLine(last.x, last.y, current.x, current.y);
            }

            last = current;
            current = new Point();
        }
    }

    private double j(int i, int n, double t) {
        return factorial(n) / (double)(factorial(i) * factorial(n - i)) *
                Math.pow(1 - t, n - i) * Math.pow(t, i) ;
    }

    private int factorial(int n) {
        if (n <= 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
