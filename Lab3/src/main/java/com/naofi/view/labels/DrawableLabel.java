package com.naofi.view.labels;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class DrawableLabel extends JLabel {
    /**
     * @return image not filled with colour
     */
    public abstract BufferedImage getRawImage();

    public void setIcon(BufferedImage image) {
        setIcon(new ImageIcon(image));
    }
}
