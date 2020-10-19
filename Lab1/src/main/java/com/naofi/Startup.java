package com.naofi;

import javax.swing.*;
import java.awt.*;

public class Startup {
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Lab 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ImagePanel());
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}
