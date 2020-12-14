package com.naofi.lab4.local;

import com.naofi.lab4.images.BarnsleyFern;
import com.naofi.lab4.images.Cloud;
import com.naofi.lab4.images.Snowflake;

import javax.swing.*;
import java.awt.*;

public class Startup {

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Lab 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane imagePanel = new JTabbedPane();
        JPanel fernPanel = new JPanel();
        JLabel fernLabel = new JLabel(new ImageIcon(new BarnsleyFern().getImage()));
        fernPanel.add(fernLabel);

        JPanel cloudPanel = new JPanel();
        JLabel cloudLabel = new JLabel(new ImageIcon(new Cloud().getImage()));
        cloudPanel.add(cloudLabel);

        JPanel snowflakePanel = new JPanel();
        JLabel snowflakeLabel = new JLabel(new ImageIcon(new Snowflake(5).getImage()));
        snowflakePanel.add(snowflakeLabel);

        imagePanel.add("Barnsley Fern", fernPanel);
        imagePanel.add("Cloud", cloudPanel);
        imagePanel.add("Snowflake", snowflakePanel);

        frame.add(imagePanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> createAndShowGUI());
    }
}
