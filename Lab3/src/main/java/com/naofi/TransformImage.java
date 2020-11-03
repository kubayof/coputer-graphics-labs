package com.naofi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Make dark pixels black and others white
 */
public class TransformImage {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(
                Objects.requireNonNull(TransformImage.class.getClassLoader().getResourceAsStream("contour.jpeg")));
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (isBlack(image, i, j) || hasBlackPixelsNearby(image, i, j)) {
                    newImage.setRGB(i, j, Color.BLACK.getRGB());
                } else {
                    newImage.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }

        ImageIO.write(newImage, "png", new FileOutputStream("contour.png"));
    }

    private static boolean hasBlackPixelsNearby(BufferedImage image, int i, int j) {
        return ((i > 0) && isBlack(image, i - 1, j)) ||
                ((i < image.getWidth() - 1) && isBlack(image, i + 1, j)) ||
                ((j > 0) && isBlack(image, i, j - 1)) ||
                ((j < image.getHeight() - 1) && isBlack(image, i, j + 1));
    }

    private static boolean isBlack(BufferedImage image, int i, int j) {
        return Math.abs(Color.WHITE.getRGB() - image.getRGB(i, j)) > Math.abs(Color.BLACK.getRGB() - image.getRGB(i, j));
    }
}
