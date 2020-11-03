package com.naofi.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Algorithms {
    private final static Color colorToFill = Color.GREEN;

    /**
     * Use breadth-first traverse to avoid StackOverflowError
     * (when using depth-first method works almost as loop)
     * Works for closed contours
     */
    public static void alg1(BufferedImage image, Point point) {
        List<Point> points = new ArrayList<>();
        points.add(point);
        List<Point> newPoints = new ArrayList<>();

        while (!points.isEmpty()) {
            for (Point p : points) {
                if (!isStop(image, p)) {
                    image.setRGB(p.x, p.y, colorToFill.getRGB());
                    newPoints.add(new Point(p.x + 1, p.y));
                    newPoints.add(new Point(p.x - 1, p.y));
                    newPoints.add(new Point(p.x, p.y + 1));
                    newPoints.add(new Point(p.x, p.y - 1));
                }
            }

            points.clear();
            points.addAll(newPoints);
            newPoints.clear();
        }
    }

    /**
     * Filling line by line
     */
    public static void alg2(BufferedImage image, Point point) {
        if ((point == null) || isStop(image, point)) {
            return;
        }

        // Fill current row
        Point p = new Point(point.x, point.y);
        int fromX;
        int toX;
        while (!isStop(image, p)) {
            image.setRGB(p.x, p.y, colorToFill.getRGB());
            p.x++;
        }
        toX = p.x - 1;

        p.x = point.x - 1;
        while (!isStop(image, p)) {
            image.setRGB(p.x, p.y, colorToFill.getRGB());
            p.x--;
        }
        fromX = p.x + 1;

        //Fill next and previous row
        p.y = point.y;
        for (int i = fromX; i <= toX; i++) {
            p.x = i;
            p.y++;
            alg2(image, p);
            p.y -= 2;
            alg2(image, p);
            p.y++;
        }
    }

    private static class StackAlgorithmContext {
        private final Deque<Point> pointsStack = new ArrayDeque<>();
        private final BufferedImage image;

        public StackAlgorithmContext(BufferedImage image) {
            this.image = image;
        }

        public void fill(Point point) {
            pointsStack.push(point);
            fill();
        }

        private void fill() {
            if (isStop(image, pointsStack.getFirst())) {
                return;
            }
            image.setRGB(pointsStack.getFirst().x, pointsStack.getFirst().y, colorToFill.getRGB());
            pointsStack.push(new Point(pointsStack.getFirst().x + 1, pointsStack.getFirst().y));
            fill();
            pointsStack.pop();
            pointsStack.push(new Point(pointsStack.getFirst().x - 1, pointsStack.getFirst().y));
            fill();
            pointsStack.pop();
            pointsStack.push(new Point(pointsStack.getFirst().x, pointsStack.getFirst().y + 1));
            fill();
            pointsStack.pop();
            pointsStack.push(new Point(pointsStack.getFirst().x, pointsStack.getFirst().y - 1));
            fill();
            pointsStack.pop();
        }
    }

    /**
     * Dumb algorithm with separate stack
     */
    public static void alg3(BufferedImage image, Point initialPoint) {
        new StackAlgorithmContext(image).fill(initialPoint);
    }

    /**
     * Dump algorithm without separate stack
     */
    public static void alg4(BufferedImage image, Point point) {
        if (isStop(image, point)) {
            return;
        }
        image.setRGB(point.x, point.y, colorToFill.getRGB());
        alg4(image, new Point(point.x + 1, point.y));
        alg4(image, new Point(point.x - 1, point.y));
        alg4(image, new Point(point.x, point.y + 1));
        alg4(image, new Point(point.x, point.y - 1));
    }

    private Algorithms() {
    }

    private static boolean isStop(BufferedImage image, Point point) {
        return isOutOfBounds(image, point) || isBlack(image, point) || isFilled(image, point);
    }

    private static boolean isOutOfBounds(BufferedImage image, Point point) {
        return (point.x < 0) ||
                (point.y < 0) ||
                (point.x >= image.getWidth()) ||
                (point.y >= image.getHeight());
    }

    private static boolean isBlack(BufferedImage image, Point point) {
        return image.getRGB(point.x, point.y) == Color.BLACK.getRGB();
    }

    private static boolean isFilled(BufferedImage image, Point point) {
        return image.getRGB(point.x, point.y) == colorToFill.getRGB();
    }
}
