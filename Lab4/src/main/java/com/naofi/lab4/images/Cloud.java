package com.naofi.lab4.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud {
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    private final int COUNT_ITER = 1000;
    private final int BAIL_OUT = 100;
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Cloud() {
        paintImage(image.getGraphics());
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void paintImage(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.DARK_GRAY);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double c_re = (col - WIDTH/2.0)*4.0/WIDTH;
                double c_im = (row - WIDTH/2.0)*4.0/WIDTH;
                double x = 0, y = 0;
                int iteration = 0;
                while (x*x+y*y <= BAIL_OUT && iteration < COUNT_ITER) {
                    double x_new = x*x - y*y + c_re;
                    y = 2*x*y + c_im;
                    x = x_new;
                    iteration++;
                }
                if (iteration >= COUNT_ITER) {
                    graphics.fillOval(col, row, 2, 2);
                }
            }
        }
        System.out.println("Done");
    }
}
