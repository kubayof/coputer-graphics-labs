package com.naofi.show;

public class DdaShow extends AbstractShow {
    public static AbstractBuilder<?> builder() {
        return new AbstractBuilder<>(new DdaShow());
    }

    private DdaShow() {}

    protected void generateImage() {
        Algorithms.ddaLine(this::point, x1, y1, x2, y2);
    }
}
