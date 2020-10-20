package com.naofi.show;

import lombok.Data;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlsPanel extends JPanel {
    @Data
    public static class Info {
        private int scale = 1;
        private int rotate;
        private boolean mirrorByX;
        private boolean mirrorByY;
        private int xDisplacement;
        private int yDisplacement;
    }

    private final ChangeListener onChangeUpdateListener;
    private JSlider scaleSlider;
    private JSlider rotateSlider;
    private JCheckBox mirrorByXCheckbox;
    private JCheckBox mirrorByYCheckbox;
    private JSlider displacementXSlider;
    private JSlider displacementYSlider;

    private final BezierView bezierView;

    public ControlsPanel(BezierView bezierView) {
        this.bezierView = bezierView;
        onChangeUpdateListener = changeEvent -> bezierView.setTransformations(getInfo());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
        setPreferredSize(new Dimension(300, 400));

        createScale();
        createRotation();
        createMirror();
        createDisplacement();
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(actionEvent -> {
            bezierView.transform(getInfo());
            resetAll();
        });
        add(applyButton);
    }

    private Info getInfo() {
        Info info = new Info();
        info.setScale(scaleSlider.getValue());
        info.setRotate(rotateSlider.getValue());
        info.setMirrorByX(mirrorByXCheckbox.isSelected());
        info.setMirrorByY(mirrorByYCheckbox.isSelected());
        info.setXDisplacement(displacementXSlider.getValue());
        info.setYDisplacement(displacementYSlider.getValue());

        return info;
    }

    private void resetAll() {
        scaleSlider.setValue(1);
        rotateSlider.setValue(0);
        mirrorByXCheckbox.setSelected(false);
        mirrorByYCheckbox.setSelected(false);
        displacementXSlider.setValue(0);
        displacementYSlider.setValue(0);

        bezierView.removeTransformations();
        bezierView.repaint();
    }

    private void createScale() {
        JPanel scalePanel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Scale");
        scalePanel.setBorder(border);

        scaleSlider = new JSlider(1, 11, 1);
        scaleSlider.setMinorTickSpacing(1);
        scaleSlider.setMajorTickSpacing(5);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setPaintLabels(true);
        scaleSlider.addChangeListener(onChangeUpdateListener);
        scalePanel.add(scaleSlider);
        add(scalePanel);
    }

    private void createRotation() {
        JPanel panel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Rotate");
        panel.setBorder(border);

        rotateSlider = new JSlider(0, 360, 0);
        rotateSlider.setMinorTickSpacing(10);
        rotateSlider.setMajorTickSpacing(120);
        rotateSlider.setPaintTicks(true);
        rotateSlider.setPaintLabels(true);
        rotateSlider.addChangeListener(onChangeUpdateListener);
        panel.add(rotateSlider);
        add(panel);
    }

    private void createMirror() {
        JPanel panel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Mirror");
        panel.setBorder(border);

        mirrorByXCheckbox = new JCheckBox("by X");
        mirrorByXCheckbox.addChangeListener(onChangeUpdateListener);
        mirrorByYCheckbox = new JCheckBox("by Y");
        mirrorByYCheckbox.addChangeListener(onChangeUpdateListener);
        panel.add(mirrorByXCheckbox);
        panel.add(mirrorByYCheckbox);

        add(panel);
    }

    private void createDisplacement() {
        JPanel panel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Displace ( X and Y )");
        panel.setBorder(border);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        displacementXSlider = new JSlider(-300, 300, 0);
        displacementXSlider.setMinorTickSpacing(50);
        displacementXSlider.setMajorTickSpacing(150);
        displacementXSlider.setPaintTicks(true);
        displacementXSlider.setPaintLabels(true);
        displacementXSlider.addChangeListener(onChangeUpdateListener);
        panel.add(displacementXSlider, BorderLayout.NORTH);

        displacementYSlider = new JSlider(-300, 300, 0);
        displacementYSlider.setMinorTickSpacing(10);
        displacementYSlider.setMajorTickSpacing(150);
        displacementYSlider.setPaintTicks(true);
        displacementYSlider.setPaintLabels(true);
        displacementYSlider.addChangeListener(onChangeUpdateListener);
        panel.add(displacementYSlider, BorderLayout.SOUTH);
        add(panel);
    }
}
