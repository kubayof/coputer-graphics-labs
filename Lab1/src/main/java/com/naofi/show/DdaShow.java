package com.naofi.show;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DdaShow extends AbstractShow {
    public static AbstractBuilder<?> builder() {
        return new AbstractBuilder<>(new DdaShow());
    }

    private DdaShow() {}

    protected void generateImage() {
        long start = System.nanoTime();
        painter.ddaLine(x1, y1, x2, y2);
        long end = System.nanoTime();
        LocalTime duration = LocalTime.ofNanoOfDay(end - start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss.nnnnnnnnn");
        System.err.println(duration.format(formatter) + " for Dda algorithm");
    }
}
