package com.naofi.view.labels;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ContourLabel extends DrawableLabel {
    @Override
    public BufferedImage getRawImage() {
        try {
            return ImageIO.read(Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("contour.png")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
