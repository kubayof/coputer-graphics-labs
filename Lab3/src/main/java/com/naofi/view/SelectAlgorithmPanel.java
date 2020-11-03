package com.naofi.view;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.Dictionary;
import java.util.Hashtable;

public class SelectAlgorithmPanel extends JPanel {
    private JSlider slider;
    private JLabel alg1speed;
    private JLabel alg2speed;
    private JLabel alg3speed;
    private JLabel alg4speed;

    public SelectAlgorithmPanel() {
        createSelectAlgorithmSlider();
        createSpeedLabels();
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
        setPreferredSize(new Dimension(350, 400));
    }

    public JSlider getSlider() {
        return slider;
    }

    public JLabel getAlg1speed() {
        return alg1speed;
    }

    public JLabel getAlg2speed() {
        return alg2speed;
    }

    public JLabel getAlg3speed() {
        return alg3speed;
    }

    public JLabel getAlg4speed() {
        return alg4speed;
    }

    private void createSelectAlgorithmSlider() {
        Dictionary<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1, new JLabel("Algorithm 1"));
        labels.put(2, new JLabel("Algorithm 2"));
        labels.put(3, new JLabel("Algorithm 3"));
        labels.put(4, new JLabel("Algorithm 4"));

        slider = new JSlider(JSlider.VERTICAL, 1, 4, 1);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        add(slider);
    }

    private void createSpeedLabels() {
        String notDeterminedMessage = "Execution time of algorithm {0} is not determined";
        alg1speed = new JLabel(MessageFormat.format(notDeterminedMessage, 1));
        alg2speed = new JLabel(MessageFormat.format(notDeterminedMessage, 2));
        alg3speed = new JLabel(MessageFormat.format(notDeterminedMessage, 3));
        alg4speed = new JLabel(MessageFormat.format(notDeterminedMessage, 4));
        add(alg1speed);
        add(alg2speed);
        add(alg3speed);
        add(alg4speed);
    }
}
