package com.naofi.show;

public class WuShow extends AbstractShow {
    public static AbstractBuilder<?> builder() {
        return new AbstractBuilder<>(new WuShow());
    }

    private WuShow() {}

    protected void generateImage() {
        Algorithms.wuLine(this::point, x1, y1, x2, y2);
    }
}
