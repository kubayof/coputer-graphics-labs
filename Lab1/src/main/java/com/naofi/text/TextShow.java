package com.naofi.text;

import com.naofi.commons.Painter;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextShow extends JLabel {
    private final int N;
    public TextShow(String text, int scale, int N) {
        this.N = N;
        int height = 9 * scale;
        int width = (int)Math.ceil(1.4 * 4 * scale * text.length()) + scale;

        setIcon(new ImageIcon(toImage(text, scale, width, height)));
    }

    private BufferedImage toImage(String text, int scale, int width, int height) {
        BufferedImage image = new BufferedImage(width * N, height * N, BufferedImage.TYPE_INT_RGB);
        Painter painter = new Painter(image.getGraphics(), N);
        painter.fillWhite(width * N, height * N);

        List<Symbol> symbols = toSymbols(text, scale, painter);

        int symbolsSize = symbols.size();
        for (int i = 0; i < symbolsSize; i++) {
            symbols.get(i).draw((int)(0.5 * scale + i * 1.4 * 4 * scale), (int)(0.5 * scale));
        }

        return image;
    }

    private List<Symbol> toSymbols(String text, int scale, Painter painter) {
        return text.chars()
                .mapToObj(i -> Symbol.of((char) i, scale, painter))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
