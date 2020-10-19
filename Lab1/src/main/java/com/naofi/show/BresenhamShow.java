package com.naofi.show;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BresenhamShow extends AbstractShow {
    public static class BresenhamShowBuilder extends AbstractBuilder<BresenhamShowBuilder> {
        public BresenhamShowBuilder() {
            super(new BresenhamShow());
        }

        public BresenhamShowBuilder circleCenter(int circleCenterX, int circleCenterY) {
            ((BresenhamShow)show).circleCenterX = circleCenterX;
            ((BresenhamShow)show).circleCenterY = circleCenterY;
            return this;
        }

        public BresenhamShowBuilder circleRadius(int circleRadius) {
            ((BresenhamShow)show).circleRadius = circleRadius;
            return this;
        }
    }

    public static AbstractBuilder<?> builder() {
        return new BresenhamShowBuilder();
    }

    private int circleCenterX = 20;
    private int circleCenterY = 20;
    private int circleRadius = 15;

    private BresenhamShow() {}

    protected void generateImage() {
        long start = System.nanoTime();
        painter.bresenhamLine(x1, y1, x2, y2);
        long end = System.nanoTime();
        LocalTime duration = LocalTime.ofNanoOfDay(end - start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss.nnnnnnnnn");
        System.err.println(duration.format(formatter) + " for Bresenham algorithm");
        painter.bresenhamCircle(circleCenterX, circleCenterY, circleRadius);
    }
}
