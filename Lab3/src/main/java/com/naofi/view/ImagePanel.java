package com.naofi.view;

import com.naofi.view.labels.ContourLabel;
import com.naofi.view.labels.PolygonLabel;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private final ContourLabel contourLabel;
    private final PolygonLabel polygonLabel;

    public ImagePanel() {
        contourLabel = new ContourLabel();
        polygonLabel = new PolygonLabel(
                new Point(10, 20),
                new Point(40, 250),
                new Point(250, 150),
                new Point(250, 30),
                new Point(100, 60));
        add(contourLabel);
        add(polygonLabel);
    }

    public ContourLabel getContourLabel() {
        return contourLabel;
    }

    public PolygonLabel getPolygonLabel() {
        return polygonLabel;
    }
}
