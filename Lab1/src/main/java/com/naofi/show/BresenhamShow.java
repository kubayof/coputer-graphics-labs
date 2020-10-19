package com.naofi.show;

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

    private int circleCenterX = 100;
    private int circleCenterY = 100;
    private int circleRadius = 50;

    private BresenhamShow() {}

    protected void generateImage() {
        Algorithms.bresenhamLine(this::point, x1, x2, y1, y2);
        Algorithms.bresenhamCircle(this::point, circleCenterX, circleCenterY, circleRadius);
    }
}
