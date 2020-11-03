package com.naofi.view;

import com.naofi.model.Algorithms;
import com.naofi.model.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ViewFacade implements Subscriber {
    protected ImagePanel imagePanel;
    protected SelectAlgorithmPanel selectAlgorithmPanel;

    public ViewFacade() {
        JFrame frame = new JFrame("Lab 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        imagePanel = new ImagePanel();
        frame.add(imagePanel, BorderLayout.CENTER);

        selectAlgorithmPanel = new SelectAlgorithmPanel();
        frame.add(selectAlgorithmPanel, BorderLayout.EAST);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public SelectAlgorithmPanel getSelectAlgorithmPanel() {
        return selectAlgorithmPanel;
    }

    @Override
    public void update(int algorithmNumber) {
        final BiConsumer<BufferedImage, Point> fillFunction = getAlgorithm(algorithmNumber);
        BiFunction<BufferedImage, Point, Long> timeMeasuringFunction = (image, point) -> {
            long start = System.nanoTime();
            fillFunction.accept(image, point);
            long end = System.nanoTime();

            return end - start;
        };
        Point startPixesCoords = new Point(100, 70);

        BufferedImage contourImage = imagePanel.getContourLabel().getRawImage();
        long executionTime = timeMeasuringFunction.apply(contourImage, startPixesCoords);
        imagePanel.getContourLabel().setIcon(contourImage);
        updateAlgTime(algorithmNumber, executionTime);

        BufferedImage polygonImage = imagePanel.getPolygonLabel().getRawImage();
        fillFunction.accept(polygonImage, startPixesCoords);
        imagePanel.getPolygonLabel().setIcon(polygonImage);
    }

    protected void updateAlgTime(int algorithmNumber, long executionTime) {
        String showSpeedMessage = "Execution time of algorithm {0}: {1}";
        String durationString = DateTimeFormatter.ofPattern("ss.nnnnnnnnn")
                .format(LocalTime.ofNanoOfDay(executionTime));
        switch (algorithmNumber) {
            case 1:
                selectAlgorithmPanel.getAlg1speed()
                        .setText(MessageFormat.format(showSpeedMessage, 1, durationString));
                break;
            case 2:
                selectAlgorithmPanel.getAlg2speed()
                        .setText(MessageFormat.format(showSpeedMessage, 2, durationString));
                break;
            case 3:
                selectAlgorithmPanel.getAlg3speed()
                        .setText(MessageFormat.format(showSpeedMessage, 3, durationString));
                break;
            case 4:
                selectAlgorithmPanel.getAlg4speed()
                        .setText(MessageFormat.format(showSpeedMessage, 4, durationString));
                break;
            default:
                throw new IllegalStateException("Unsupported algorithm number: " + algorithmNumber);
        }
    }

    protected BiConsumer<BufferedImage, Point> getAlgorithm(int number) {
        switch (number) {
            case 1:
                return Algorithms::alg1;
            case 2:
                return Algorithms::alg2;
            case 3:
                return Algorithms::alg3;
            case 4:
                return Algorithms::alg4;
            default:
                throw new IllegalStateException("Non existing algorithm with №" + number);
        }
    }
}
