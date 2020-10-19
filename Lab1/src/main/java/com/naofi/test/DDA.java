package com.naofi.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DDA extends JLabel {
    public DDA() {
        int size = 1000;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        double dx, dy, steps, x, y;
        double xc, yc;
        double x1 = 200, y1 = 500, x2 = 600, y2 = 200;
        dx = x2 - x1;
        dy = y2 - y1;
        steps = Math.max(Math.abs(dx), Math.abs(dy));

        xc = (dx / steps);
        yc = (dy / steps);
        x = x1;
        y = y1;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        image.setRGB((int)x, (int)y, Color.BLACK.getRGB());
        for (double k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            image.setRGB((int)x, (int)y, Color.BLACK.getRGB());
        }

        setIcon(new ImageIcon(image));
    }
//    public void paint(Graphics g) {
//        double dx, dy, steps, x, y, k;
//        double xc, yc;
//        double x1 = 200, y1 = 500, x2 = 600, y2 = 200;
//        dx = x2 - x1;
//        dy = y2 - y1;
//        steps = Math.max(Math.abs(dx), Math.abs(dy));
//
//        xc = (dx / steps);
//        yc = (dy / steps);
//        x = x1;
//        y = y1;
//        g.fillOval(200, 500, 5, 5);
//        for (k = 1; k <= steps; k++) {
//            x = x + xc;
//            y = y + yc;
//            g.fillOval((int) x, (int) y, 5, 5);
//        }
//    }

    public static void main(String[] args) {
        DDA m = new DDA();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(400,400);
        //f.setLayout(null);
        f.setVisible(true);
    }
}
