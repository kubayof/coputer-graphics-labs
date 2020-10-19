package com.naofi;

import com.naofi.show.BresenhamShow;
import com.naofi.show.DdaShow;
import com.naofi.show.WuShow;
import com.naofi.text.TextShow;

import javax.swing.*;

public class ImagePanel extends JPanel {
    public ImagePanel() {
        int imageSize = 1000;
        // Pseudo pixel N*N
        int n = 5;
        add(DdaShow.builder()
                .size(imageSize)
                .pseudoPixelSize(n)
                .build());
        add(BresenhamShow.builder()
                .size(imageSize)
                .pseudoPixelSize(n)
                .build());
        add(WuShow.builder()
                .size(imageSize)
                .pseudoPixelSize(n)
                .build());
        add(new TextShow("kubai", 10, n));
    }
}
