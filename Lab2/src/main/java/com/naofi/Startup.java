package com.naofi;

import com.naofi.show.BezierView;
import com.naofi.show.ControlsPanel;

import javax.swing.*;
import java.awt.*;

public class Startup {
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Lab 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        BezierView bezierView = new BezierView(500, 3,
                new Point(100, 200),
                new Point(100, 100),
                new Point(200, 100),
                new Point(200, 200),
                new Point(300, 200),
                new Point(300, 100));
        frame.add(bezierView, BorderLayout.CENTER);

        frame.add(new ControlsPanel(bezierView), BorderLayout.EAST);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> createAndShowGUI());
    }
}
